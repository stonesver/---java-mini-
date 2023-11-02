public class Main {
    public static void main(String[] args) {

        String inputfile= "source1.c";
        String outputfile="result.c";
        Analysis analysis = new Analysis();
        analysis.ReadFile(inputfile);
        analysis.output();
    }
}