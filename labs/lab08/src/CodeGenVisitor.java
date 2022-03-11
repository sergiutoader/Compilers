import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

public class CodeGenVisitor implements ASTVisitor<ST>{
	static STGroupFile templates = new STGroupFile("cgen.stg");
	
	ST mainSection = templates.getInstanceOf("sequenceSpaced");	// filled directly (through visitor returns)
	ST dataSection = templates.getInstanceOf("sequenceSpaced"); 	// filled collaterally ("global" access)
	ST funcSection = templates.getInstanceOf("sequence"); // filled collaterally ("global" access)

	int ifIdx = 0;

	/* 
	 * Plain numbers
	 * TODO 1:
	 */

    @Override
    public ST visit(Int val) {
        return templates.getInstanceOf("literal")
                .add("value", val.getToken().getText());
    }
    
    @Override
    public ST visit(FloatNum val) {
		String str = val.token.getText();
		int idx = str.indexOf(".");

		var st = templates.getInstanceOf("literal_float");
		if (idx != -1) {
			st.add("int_val", str.substring(0, idx));
			st.add("dec_val", str.substring(idx + 1));
		} else {
			st.add("int_val", str);
			st.add("dec_val", "0");
		}
		st.add("dStr", val.debugStr);

		return st;
    }

    @Override
    public ST visit(Bool val) {
		var text = val.getToken().getText();
		if (text.equals("true")) {
			return templates.getInstanceOf("literal_true");
		} else if (text.equals("false")) {
			return templates.getInstanceOf("literal_false");
		}

		return null;
    }
    
    /* 
     * Unary operations
     * TODO 1:
     */
    
	@Override
	public ST visit(UnaryMinus uMinus) {
		var st = templates.getInstanceOf("uminus");
		st.add("e", uMinus.expr.accept(this))
				.add("dStr", uMinus.debugStr);
		return st;
	}
    
	/* 
	 * Binary operations
	 * TODO 2:
	 */
    
    @Override
    public ST visit(Plus expr) {
		var st = templates.getInstanceOf("arithmetic");
		st.add("e1", expr.left.accept(this))
    		.add("e2",  expr.right.accept(this))
			.add("op", "add $a0 $t1 $a0")
			.add("dStr", expr.debugStr);
    	
    	return st;
    }
    
    @Override
    public ST visit(Minus expr) {
		var st = templates.getInstanceOf("arithmetic");
		st.add("e1", expr.left.accept(this))
				.add("e2",  expr.right.accept(this))
				.add("op", "add $a0 $t1 $a0")
				.add("dStr", expr.debugStr);
		return st;
    }
    
    @Override
    public ST visit(Mult expr) {
		var st = templates.getInstanceOf("arithmetic");
		st.add("e1", expr.left.accept(this))
				.add("e2",  expr.right.accept(this))
				.add("op", "mul $a0 $t1 $a0")
				.add("dStr", expr.debugStr);

		return st;
    }
    
    @Override
    public ST visit(Div expr) {
		var st = templates.getInstanceOf("arithmetic");
		st.add("e1", expr.left.accept(this))
				.add("e2",  expr.right.accept(this))
				.add("op", "div $t1 $a0\nmfhi $a0")
				.add("dStr", expr.debugStr);

		return st;
    }
	
	@Override
	public ST visit(Relational expr) {
		var st = templates.getInstanceOf("arithmetic");
		st.add("e1", expr.left.accept(this))
				.add("e2",  expr.right.accept(this))
				.add("dStr", expr.debugStr);
		var text = expr.token.getText();
		if (text.equals("<")) {
			st.add("op", "sle $a0, $t1 $a0");
		} else if (text.equals("<=")) {
			st.add("op", "slt $a0, $t1 $a0");
		} else if (text.equals("==")) {
			st.add("op", "seq $a0, $t1 $a0");
		}
		return st;
	}

    /*
     * Control structures
     * TODO 3:
     */
    
    @Override
	public ST visit(If iff) {
		var st = templates.getInstanceOf("conditional");
		st.add("cond", iff.cond.accept(this))
				.add("exprThen", iff.thenBranch.accept(this))
				.add("exprElse", iff.elseBranch.accept(this))
				.add("idx", ifIdx++)
				.add("dStr", iff.debugStr);

		return st;
	}

	@Override
	public ST visit(Call call) {
		return null;
	}

    /*
     * Definitions & assignments
     * TODO 4&5:
     */

	@Override
	public ST visit(Assign assign) {
		// TODO 4: generare cod pentru main()
		var st = templates.getInstanceOf("assign")
				.add("id", assign.id.accept(this))
				.add("e", assign.expr.accept(this))
				.add("dStr", assign.debugStr);
		return st;
	}

	@Override
	public ST visit(VarDef varDef) {
		// TODO 4: generare cod pentru main() și etichetă în .data
		var id = varDef.id.getToken().getText();
		var type = varDef.type.token.getText();
		ST initValue = null;


		ST st = null;
		ST dataST = null;
		if (type.equals("Int")) {
			dataST = templates.getInstanceOf("intDecl");
		} else if (type.equals("Float")) {
			dataST = templates.getInstanceOf("floatDecl");
		} else {
			dataST = templates.getInstanceOf("boolDecl");
		}
		dataST.add("id", id);


		dataSection.add("e", dataST);

		if (varDef.initValue != null) {
			initValue = varDef.initValue.accept(this);
			st = templates.getInstanceOf("assign")
					.add("id", id)
					.add("e", initValue)
					.add("dStr", varDef.debugStr);
		}

		return st;
	}

	@Override
	public ST visit(FuncDef funcDef) {
		// TODO 5: generare cod pentru funcSection. Fără cod în main()!
		return null;
	}

	/*
	 * META
	 */
	
	@Override
	public ST visit(Id id) {
		// TODO 5
		return null;
	}

	@Override
	public ST visit(Formal formal) {
		// TODO 5
		return null;
	}
	
	@Override
	public ST visit(Type type) {
		return null;
	}

	@Override
	public ST visit(Program program) {
		dataSection = templates.getInstanceOf("sequenceSpaced");
		funcSection = templates.getInstanceOf("sequenceSpaced");
		mainSection = templates.getInstanceOf("sequence");
		
		for (ASTNode e : program.stmts)
			mainSection.add("e", e.accept(this));
		
		//assembly-ing it all together. HA! get it?
		var programST = templates.getInstanceOf("program");
		programST.add("data", dataSection);
		programST.add("textFuncs", funcSection);
		programST.add("textMain", mainSection);
		
		return programST;
	}

}
