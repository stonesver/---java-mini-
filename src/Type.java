public class Type {
    //identifier
    public static final int DIGIT = 1;//数字
    public static final int LETTER_ = 2;//字母加下划线

    //keyword

    //基本类型
    public static final int INT = 3;
    public static final int DOUBLE = 4;
    public static final int CHAR = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int SHORT = 8;
    public static final int VOID = 9;

    //控制语句
    public static final int IF = 10;
    public static final int ELSE = 11;
    public static final int FOR = 12;
    public static final int WHILE = 13;
    public static final int DO = 14;
    public static final int SWITCH = 15;
    public static final int CASE = 16;
    public static final int BREAK = 17;
    public static final int CONTINUE = 18;
    public static final int RETURN = 19;


    //operator-token and punctuator 运算符和界符
    public static final int ASSIGN = 20;//=
    public static final int ADD = 21;//+
    public static final int SUB = 22;//-
    public static final int MUL = 23;//*
    public static final int DIV = 24;// /
    public static final int LT = 25;//<
    public static final int LE = 26;//<=
    public static final int GT = 27;//>
    public static final int GE = 28;//>=
    public static final int NE = 29;//!=
    public static final int EQUAL = 30;//==
    public static final int OR_1 = 31;//|
    public static final int OR_2 = 32;//||
    public static final int AND_1 = 33;//&
    public static final int AND_2 = 34;//&&
    public static final int NOT = 35;//!
    public static final int XOR = 36;//^
    public static final int INCREASE = 37;//++
    public static final int DECREASE = 38;//--
    public static final int COMMA = 39;//,
    public static final int SEMICOLON = 40;//;
    public static final int BRACE_L = 41;//{
    public static final int BRACE_R = 42;//}
    public static final int BRACKET_L = 43;//[
    public static final int BRACKET_R = 44;//]
    public static final int PARENTHESIS_L = 45;//(
    public static final int PARENTHESIS_R = 46;//)
    public static final int QUOTATION = 47;//"
    public static final int APOSTROPHE = 48;//'
    public static final int POINT = 49;//.
    public static final int POUND = 50;//#

    //literal常量
    public static final int STRING = 50;//字符串
    public static final int CHAR_LITERAL = 51;//字符
    public static final int INT_LITERAL = 52;//整型
    public static final int FLOAT_LITERAL = 53;//浮点型
    public static final int BOOL_LITERAL = 54;//布尔型
    public static final int NULL_LITERAL = 55;//空指针

    //根据int值映射名称
    public static String getName(int type) {
        switch (type) {
            case 1:
                return "DIGIT";
            case 2:
                return "LETTER_";
            case 3:
                return "INT";
            case 4:
                return "DOUBLE";
            case 5:
                return "CHAR";
            case 6:
                return "FLOAT";
            case 7:
                return "LONG";
            case 8:
                return "SHORT";
            case 9:
                return "VOID";
            case 10:
                return "IF";
            case 11:
                return "ELSE";
            case 12:
                return "FOR";
            case 13:
                return "WHILE";
            case 14:
                return "DO";
            case 15:
                return "SWITCH";
            case 16:
                return "CASE";
            case 17:
                return "BREAK";
            case 18:
                return "CONTINUE";
            case 19:
                return "RETURN";
            case 20:
                return "ASSIGN";
            case 21:
                return "ADD";
            case 22:
                return "SUB";
            case 23:
                return "MUL";
            case 24:
                return "DIV";
            case 25:
                return "LT";
            case 26:
                return "LE";
            case 27:
                return "GT";
            case 28:
                return "GE";
            case 29:
                return "NE";
            case 30:
                return "EQUAL";
            case 31:
                return "OR_1";
            case 32:
                return "OR_2";
            case 33:
                return "AND_1";
            case 34:
                return "AND_2";
            case 35:
                return "NOT";
            case 36:
                return "XOR";
            case 37:
                return "INCREASE";
            case 38:
                return "DECREASE";
            case 39:
                return "COMMA";
            case 40:
                return "SEMICOLON";
            case 41:
                return "BRACE_L";
            case 42:
                return "BRACE_R";
            case 43:
                return "BRACKET_L";
            case 44:
                return "BRACKET_R";
            case 45:
                return "PARENTHESIS_L";
            case 46:
                return "PARENTHESIS_R";
            case 47:
                return "QUOTATION";
            case 48:
                return "APOSTROPHE";
            case 49:
                return "POINT";
            case 50:
                return "POUND";
            case 51:
                return "STRING";
            case 52:
                return "CHAR_LITERAL";
            case 53:
                return "INT_LITERAL";
            case 54:
                return "FLOAT_LITERAL";
            case 55:
                return "BOOL_LITERAL";
            case 56:
                return "NULL_LITERAL";
            default:
                return "UNKNOWN";
        }
    }
}
