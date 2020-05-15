import java.util.*;
import java.lang.*;

class Polynomial {
private Term[] termArray;
private int terms;  // number of nonzero terms

class Term {
private double coef; //coefficient
private int exp; //exponent
};

// constructor
Polynomial(int cap) {
termArray = new Term[cap];
terms = 0;
}

/**
*  Add a new term to the end of termArray
*/
public void NewTerm(double theCoeff, int theExp) {
if(terms == termArray.length)
{// double capacity of termArray
termArray = Arrays.copyOf(termArray , termArray.length * 2);
}
termArray[terms] = new Term();
termArray[terms].coef = theCoeff;
termArray[terms++].exp = theExp;
}

public Polynomial Add(Polynomial b) {
// Return the sum of the polynomials this and b
Polynomial c = new Polynomial(128);
int aPos = 0, bPos = 0;
while((aPos < terms) && (bPos < b.terms))
if(termArray[aPos].exp == b.termArray[bPos].exp){
double t=termArray[aPos].coef+b.termArray[bPos].coef;
if (t != 0.0) c.NewTerm(t, termArray[aPos].exp);
aPos++; bPos++;
}
else if(termArray[aPos].exp < b.termArray[bPos].exp){
c.NewTerm(b.termArray[bPos].coef, b.termArray[bPos].exp);
bPos++;
}
else {
c.NewTerm(termArray[aPos].coef, termArray[aPos].exp);
aPos++;
}

// add in remaining terms of *this
for( ; aPos < terms; aPos++)
c.NewTerm(termArray[aPos].coef, termArray[aPos].exp);
for( ; bPos < b.terms; bPos++)
c.NewTerm(b.termArray[bPos].coef, b.termArray[bPos].exp);
return c;
}

public Polynomial Subtract(Polynomial b) {
// Return the sum of the polynomials this and b
Polynomial d = new Polynomial(128);

int Aposit = 0 , Bposit = 0;

while( (Aposit < terms) && (Bposit < b.terms))
{
if(termArray[Aposit].exp == b.termArray[Bposit].exp)
  {
    double temp = termArray[Aposit].coef - b.termArray[Bposit].coef;
    if(temp != 0.0)
      {
        d.NewTerm(temp , termArray[Aposit].exp);
      }
    Aposit++;
    Bposit++;
  }
else if(termArray[Aposit].exp > b.termArray[Bposit].exp)
{
d.NewTerm(termArray[Aposit].coef, termArray[Aposit].exp);
Aposit++;
}
else
{
d.NewTerm(-1 * b.termArray[Bposit].coef , b.termArray[Bposit].exp);
Bposit++;
}
}

for( ; Aposit < terms ; Aposit++)
{
d.NewTerm(termArray[Aposit].coef, termArray[Aposit].exp);
}

for( ; Bposit < b.terms ; Bposit++)
{
	double tempp = -1 * b.termArray[Bposit].coef ;
   d.NewTerm( tempp , b.termArray[Bposit].exp);
}

return d;
}


double Evaluate(double f) {
double eval = 0.0;

for(int i = 0 ; i < terms ; i++)
{
double temp = termArray[i].coef * Math.pow( f , termArray[i].exp);
eval += temp;
}

return eval;
}

}
