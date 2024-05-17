package alex;

import errors.GestionErroresExp;

%%
%cup
%line
%column
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

guion = \_
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
numeroEntero = ({digitoPositivo}{digito}*) | 0
separador = [ \t\r\b\n]
comentario = \/\/[^\n]* 
operadorSuma = \+
operadorResta = \-
operadorDivision = \/
parentesisApertura = \(
parentesisCierre = \)
corchetesApertura = \[
corchetesCierre = \]
llavesApertura = \{
llavesCierre = \}
coma  = \,
pComa = \;

asig = \:\=
opAsterisco = \*
opPunto = \.
opMasMas = \+\+
opMenosMenos = \-\-
opPasoValor = \$
igual = \=\=
distinto = \=\/\=
negacion = not
conjuncion = \&\&
disyuncion = \|\|
menorQue = \<
mayorQue = \>
menorIgual = \<\=
mayorIgual = \>\=
new = new
prints = prints
scans = scans

while = while
for = for
switch = switch
case = case
default = default
return = return
if = if
elsif = elsif
else = else

class = class
struct = struct
void = void
int = int
bool = bool
true = True
false = False

letra  = ([A-Z]|[a-z])
identificador = {letra}({letra}|{digito}|{guion})*

%%
{separador}               {}
{comentario}              {}
{numeroEntero}            {return ops.unidadEnt();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();}
{corchetesApertura}       {return ops.unidadCAp();}
{corchetesCierre}         {return ops.unidadCCierre();}
{llavesApertura}      	  {return ops.unidadLlAp();}
{llavesCierre}        	  {return ops.unidadLlCierre();} 
{coma}                    {return ops.unidadComa();}
{pComa}                   {return ops.unidadPComa();}

{asig}                    {return ops.unidadAsig();}

{opMasMas}             	  {return ops.unidadOpMasMas();}
{opMenosMenos}            {return ops.unidadOpMenosMenos();}
{opAsterisco}		  {return ops.unidadOpAsterisco();}
{opPunto}                 {return ops.unidadOpPunto();}
{opPasoValor}             {return ops.unidadOpPasoValor();}
{igual}                   {return ops.unidadIgual();}
{distinto}                {return ops.unidadDistinto();}
{negacion}                {return ops.unidadNegacion();}
{conjuncion}              {return ops.unidadConjuncion();}
{disyuncion}              {return ops.unidadDisyuncion();}
{menorQue}                {return ops.unidadMenorQue();}
{mayorQue}                {return ops.unidadMayorQue();}
{menorIgual}              {return ops.unidadMenorIgual();}
{mayorIgual}              {return ops.unidadMayorIgual();}
{new}                     {return ops.unidadNew();}
{prints}                  {return ops.unidadPrints();}
{scans}                   {return ops.unidadScans();}


{while}           	  {return ops.unidadWhile();}
{for}           	  {return ops.unidadFor();}
{switch}           	  {return ops.unidadSwitch();}
{case}            	  {return ops.unidadCase();}
{default}           	  {return ops.unidadDefault();}
{return}           	  {return ops.unidadReturn();}
{if}           		  {return ops.unidadIf();}
{elsif}           	  {return ops.unidadElsif();}
{else}           	  {return ops.unidadElse();}

{class}           	  {return ops.unidadClass();}
{struct}           	  {return ops.unidadStruct();}
{void}           	  {return ops.unidadVoid();}
{int}           	  {return ops.unidadInt();}
{bool}           	  {return ops.unidadBool();}
{true}           	  {return ops.unidadTrue();}
{false}           	  {return ops.unidadFalse();}

{identificador}           {return ops.unidadId();}

[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
