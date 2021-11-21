Nume: Toader Sergiu-Cristian
Grupa: 344C1

Tema 0 - Compilatoare


Fisiere adaugate, exceptand cele deja existente in scheletul temei:
- stringTokenizer.cl - contine o implementare de word split pe un string,
asemanatoare cu clasa StringTokenizer din Java. Expune metodele hasNext() si
next() pentru extragerea cuvintelor dintr-un string.
-a2i.cl - fisier preluat din arhiva de la curs, utilizat pentru conversia
Integer-String si String-Integer

Listele sunt implementate in fisierul list.cl.

Ierarhia claselor pentru implementarea listei este:

   List
    |
LinkedList
  	|
   Node

List este clasa principala care expune API-ul folosit in tema (merge, filterBy,
sortBy, toString). Contine si alte metode precum: add, delete, getList. Are un
singur camp - list care este de tipul LinkedList si care contine nodurile
prezente in lista.

LinkedList este clasa care contine implementarea pentru operatiile pe lista.
Are doua campuri: head - referinta la primul nod din lista si size - numarul
de noduri din lista. Contine implementarea pentru metodele: cons, delete,
toString, merge, filterBy, sortBy, get, toate avand ca model operatiile pe liste
simplu inlantuite (parcurgere prin referinte, modificarea legaturilor, etc).
Mai multe informatii despre implementarea metodelor se gasesc in cod.

Node este clasa de baza pentru un nod din lista simplu inlantuita. Contine un
camp pentru valoarea stocata si un camp pentru referinta catre urmatorul nod.
Sfarsitul listei se marcheaza printr-un nod vid (neinitializat).


Filtrele si comparatorii se gasesc in util.cl. Pentru filtre am verificat
tipul dinamic in cazul filtrelor de Product si Rank, respectiv pretul clasei
curente raportat la pretul clasei parinte in cazul filtrului de pret.
Comparatoarele se folosesc de pret in cazul produselor, de o valoare a rank-ului
intoarsa de functia getRankScore() in cazul rangurilor militare si de comparatia
alfanumerica a string-urilor folosind operatorul "<" in cazul sirurilor de
caractere.

In fisierul util.cl se gaseste si clasa Utils care contine diverse functii
ajutatoare, cu scopul de conversie de la un tip la altul sau de instantiere
a unor obiecte in functie de tipul citit de la input.

Fisierul main.cl contine metoda main, ce consta dintr-un while in care se
realizeaza 2 operatii, reprezentate de 2 metode:
	- objectParsing()
	- commandParsing()

- In clasa main exista campul lists care va stoca fiecare lista citita de la
input
- list este un camp prin care se construieste lista curenta
- main_looping si looping sunt variabilele prin care se controleaza buclele
din program, respectiv din functiile de parsare

- objectParsing()
	- se incheie in momentul citirii liniei "END"
	- citeste o linie si extrage primul cuvant pentru a determina tipul
	obiectului creat
	- in functie de cuvantul citit, instantiaza obiectul
	- citeste celelalte argumente, setandul-le pe obiect
	- adauga obiectul in lista curenta 
	- repeta procesul

- commandParsing()
	- se incheie in momentul citirii comenzii "load" sau cand fisierul de
	intrare s-a terminat
	- citeste o linie si extrage primul cuvant pentru a determina tipul
	operatiei executate (print, merge, filterBy, sortBy)
		- print - daca exista argumente pentru print, se citeste index-ul, se
		extrage lista dorita si se afiseaza prin apelul metodei toString pe
		lista. Daca linia nu mai contine argumente, se vor afisa toate listele,
		indexate la inceput cu numarul listei
		- merge - se citesc indicii listelor, se extrag pe baza lor din lista
		acele liste, se aplica metoda merge pe prima lista cu a doua lista ca
		argument si se adauga rezultatul in lista la final. Cele doua liste
		vechi se sterg.
		- filterBy - se citeste indexul listei si tipul de filter, se creeaza o
		instanta pentru filtru in functie de tip, se extrage lista pe baza
		indexului si se apeleaza metoda filterBy pe lista cu filtul ca argument
		- sortBy - se citeste indexul listei, tipul de comparator si modul de
		sortare (crescator / descrescator), se creeaza o instanta pentru
		comparator in functie de tip, se extrage lista pe baza indexului si se
		apeleaza metoda sortBy pe lista cu filtrul si modul de sortare ca
		argumente

