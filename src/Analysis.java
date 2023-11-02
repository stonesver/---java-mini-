import java.io.File;
import java.io.FileInputStream;

public class Analysis {
    String[] keywords = {
            "int", "double", "char", "float", "long", "short", "void", "if", "else", "for", "while", "do", "switch", "case", "break", "continue", "return", "bool"
    };
    String input = "";
    int start = 0;
    int search = 0;

    boolean flagDoubleQuotation = false;
    boolean flagSingleQuotation = false;

    public void ReadFile(String inputfile) {
        File sourceFile = new File(inputfile);
        //打开文件逐个字符读取，删去空白，换行，制表符
        //保留字符串内的空白
        try {
            FileInputStream in = new FileInputStream(sourceFile);
            char ch1 = ' ';
            while (in.available() > 0) {
                ch1 = (char) in.read();
                if (ch1 == '\'') {
                    input += ch1;
                    while (in.available() > 0) {
                        ch1 = (char) in.read();
                        if (ch1 == '\'') {
                            break;
                        }
                        input += ch1;
                    }
                }
                if (ch1 == '\"') {
                    input += ch1;
                    while (in.available() > 0) {
                        ch1 = (char) in.read();
                        if (ch1 == '\"') {
                            break;
                        }
                        input += ch1;
                    }
                }
                if (ch1 == ' ' || ch1 == '\n' || ch1 == '\t' || ch1 == '\r') {
                    continue;
                } else {
                    input += ch1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取预处理后的字符串
    public String getInput() {
        return input;
    }

    //判断是否为数字
    public boolean isNum(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }

    //判断是否为字母和下划线
    public boolean isLetter(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '_')) {
            return true;
        }
        return false;
    }

    //获取下一个字符
    public char nextChar(int index) {
        char ch = input.charAt(index);
        return ch;
    }

    //分析字符串
    public Token analyic() {
        Token token = new Token(0, "");
            token = new Token(0, "");
            search = start;
            char ch = nextChar(start);
            if (flagDoubleQuotation) {
                String value = "";
                while (ch != '"') {
                    value += ch;
                    search++;
                    ch=nextChar(search);
                }
                start=search;
                if (!value.isEmpty()) {
                    token.type = Type.STRING;
                    token.value = value;
                    return token;
                }
            }
            //开头为数字
            if (isNum(ch)) {
                boolean tag=false;
                token.type=Type.DIGIT;
                String value = "";
                search++;
                value += ch;
                ch=nextChar(search);
                while (isNum(ch) || ch == '.') {
                    //判断是否为浮点数
                    if(!tag){
                        token.type = Type.INT_LITERAL;
                    }
                    if (ch == '.') {
                        token.type = Type.FLOAT_LITERAL;
                        tag=true;
                    }
                    value += ch;
                    search++;
                    ch = nextChar(search);
                }
                start = search;
                token.value = value;
                return token;
            }
            //开头为字母
            if (isLetter(ch)) {
                String value = "";
                while (isLetter(ch) || isNum(ch)) {
                    value += ch;
                    search++;
                    //判断是否为关键字
                    for (int i = 0; i < keywords.length; i++) {
                        if (value.equals(keywords[i])) {
                            token.type = i + 3;
                            token.value = value;
                            start = search;
                            return token;
                        }
                    }
                    //判断是否为常量
                    if (value.equals("true") || value.equals("false")) {
                        token.type = Type.BOOL_LITERAL;
                        token.value = value;
                        start = search;
                        return token;
                    }
                    if (value.equals("nullptr")) {
                        token.type = Type.NULL_LITERAL;
                        token.value = value;
                        start = search;
                        return token;
                    }
                    ch = nextChar(search);
                }
                start = search;
                token = new Token(Type.LETTER_, value);
                return token;
            }
            //开头为运算符
            switch (ch) {
                case '+':
                    if (nextChar(search + 1) == '+') {
                        token.type = Type.INCREASE;
                        token.value = "++";
                        start = start + 2;
                    } else {
                        start++;
                        token.type = Type.ADD;
                        token.value = "+";
                    }
                    break;
                case '-':
                    if (nextChar(search + 1) == '-') {
                        token.type = Type.DECREASE;
                        token.value = "--";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.SUB;
                        token.value = "-";
                    }
                    break;
                case '*':
                    token.type = Type.MUL;
                    token.value = "*";
                    start++;
                    break;
                case '/':
                    token.type = Type.DIV;
                    token.value = "/";
                    start++;
                    break;
                case '=':
                    if (nextChar(search + 1) == '=') {
                        token.type = Type.EQUAL;
                        token.value = "==";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.ASSIGN;
                        token.value = "=";
                    }
                    break;
                case ';':
                    token.type = Type.SEMICOLON;
                    token.value = ";";
                    start++;
                    break;
                case '(':
                    token.type = Type.PARENTHESIS_L;
                    token.value = "(";
                    start++;
                    break;
                case ')':
                    token.type = Type.PARENTHESIS_R;
                    token.value = ")";
                    start++;
                    break;
                case '{':
                    token.type = Type.BRACE_L;
                    token.value = "{";
                    start++;
                    break;
                case '}':
                    token.type = Type.BRACE_R;
                    token.value = "}";
                    start++;
                    break;
                case '[':
                    token.type = Type.BRACKET_L;
                    token.value = "[";
                    start++;
                    break;
                case ']':
                    token.type = Type.BRACKET_R;
                    token.value = "]";
                    start++;
                    break;
                case ',':
                    token.type = Type.COMMA;
                    token.value = ",";
                    start++;
                    break;
                case '\'':
                    flagSingleQuotation = !flagSingleQuotation;
                    token.type = Type.APOSTROPHE;
                    token.value = "'";
                    start++;
                    break;
                case '\"':
                    flagDoubleQuotation = !flagDoubleQuotation;
                    token.type = Type.QUOTATION;
                    token.value = "\"";
                    start++;
                    break;
                case '#':
                    token.type = Type.POUND;
                    token.value = "#";
                    start++;
                    break;
                case '&':
                    if (nextChar(search + 1) == '&') {
                        token.type = Type.AND_2;
                        token.value = "&&";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.AND_1;
                        token.value = "&";
                    }
                    break;
                case '|':
                    if (nextChar(search + 1) == '|') {
                        token.type = Type.OR_2;
                        token.value = "||";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.OR_1;
                        token.value = "|";
                    }
                    break;
                case '!':
                    if (nextChar(search + 1) == '=') {
                        token.type = Type.NE;
                        token.value = "!=";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.NOT;
                        token.value = "!";
                    }
                    break;
                case '>':
                    if (nextChar(search + 1) == '=') {
                        token.type = Type.GE;
                        token.value = ">=";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.GT;
                        token.value = ">";
                    }
                    break;
                case '<':
                    if (nextChar(search + 1) == '=') {
                        token.type = Type.LE;
                        token.value = "<=";
                        start = start + 2;
                        return token;
                    } else {
                        start++;
                        token.type = Type.LT;
                        token.value = "<";
                    }
                    break;
                case '.':
                    start++;
                    token.type = Type.POINT;
                    token.value = ".";
                    break;
                case '^':
                    start++;
                    token.type = Type.XOR;
                    token.value = "^";
                    break;
                default:
                    break;
            }
        return token;
    }

    public void output(){
        //输出预处理后的程序
        System.out.println("预处理后的程序:");
        for(int i=0;i<input.length();i++){
            //输出为一行
            System.out.print(input.charAt(i));
        }
        //换行
        System.out.println();
        System.out.println("词法分析结果:");
        while(start<input.length()){
            Token token = analyic();
            //System.out.println("<"+token.type+" , "+token.value+">");
            //输出token.type对应Type类中的常量名称
            System.out.println("<"+Type.getName(token.type)+","+token.value+">");
        }
    }
}
