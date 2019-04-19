%{
	FILE* yyin;
%}
DIGIT		[0-9]
NUMBER 		{DIGIT}+
REAL		{DIGIT}*[.]{NUMBER}
TEXT		[a-zA-Z]
KEYWORDS	"class"|"static"
DATATYPE	"int"|"float"|"double"|"boolean"|"long"|"void"|"String"|"char"
CONDITIONAL	"if"|"else if"|"else"|"switch"|"case"
ITERATIVE	"for"|"do"|"while"
PREPROCESSOR	"import"[^\n]*";"
SC		";"
IDENTIFIER	{TEXT}({DIGIT}|{TEXT}|"_")*
NONIDENTIFIER	{DIGIT}({DIGIT}|{TEXT}|"_")*
ARITH_OP	"+"|"-"|"*"|"/"|"%"
LOGICAL_OP	"&&"|"||"|"!="|"="
RELATIONAL_OP	">"|">="|"<"|"<="|"=="
UNARY		"++"|"--"
ACCESS		"public"|"private"|"protected"
FUNCTION	{ACCESS}{DATATYPE}{IDENTIFIER}"("({DATATYPE}{IDENTIFIER})*")"

%%

[ \n\t]+	;
{DIGIT}		{printf("%s\t ==> DIGIT\n",yytext);}
{NUMBER}	{printf("%s\t ==> NUMBER\n", yytext);}
{REAL}		{printf("%s\t ==> REAL\n", yytext);}
{TEXT}		{printf("%s\t ==> TEXT\n", yytext);}
{KEYWORDS}	{printf("%s\t ==> KEYWORDS\n", yytext);}
{DATATYPE}	{printf("%s\t ==> DATATYPE\n", yytext);}
{CONDITIONAL}	{printf("%s\t ==> CONDITIONAL\n", yytext);}
{ITERATIVE}	{printf("%s\t ==> ITERATIVE\n", yytext);}
{PREPROCESSOR}	{printf("%s\t ==> PREPROCESSOR\n", yytext);}
{SC}		{printf("%s\t ==> DELIMITER\n", yytext);}
{IDENTIFIER}	{printf("%s\t ==> IDENTIIFER\n", yytext);}
{NONIDENTIFIER}	{printf("%s\t ==> NONIDENTIFIER\n", yytext);}
{ARITH_OP}	{printf("%s\t ==> ARITHMATIC OPERATOR\n", yytext);}
{LOGICAL_OP}	{printf("%s\t ==> LOGICAL OPERATOR\n", yytext);}
{RELATIONAL_OP}	{printf("%s\t ==> RELATIONAL OPERATOR\n", yytext);}
{UNARY}		{printf("%s\t ==> UNARY OPERATOR\n", yytext);}
{ACCESS}	{printf("%s\t ==> ACCESS SPECIFIER\n", yytext);}
{FUNCTION}	{printf("%s\t ==> FUNCTION\n", yytext);}
"="		{printf("%s\t ==> ASSIGNMENT OPERATOR\n", yytext);}
"{"		{printf("%s\t ==> BLOCK BEGIN\n", yytext);}
"}"		{printf("%s\t ==> BLOCK END\n", yytext);}
"("		{printf("%s\t ==> PARANTHESIS BEGIN\n", yytext);}
")"		{printf("%s\t ==> PARANTHESIS END\n", yytext);}
.		;

%%

int yywrap() {
	return 1;
}

int main(argc, char *argv[]) {
	yyin = fopen(argv[1], "r");
	yylex();
	fclose(yyin);
}
