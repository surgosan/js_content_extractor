import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Is your html file in the html directory? Write 'true' or 'false'");
        boolean htmlDirectory = in.nextBoolean();
        in.nextLine();

        System.out.println("Is your html file in the sub_documentation directory? Write 'true' or 'false'");
        boolean sub_doc_directory = in.nextBoolean();
        in.nextLine();

        System.out.println("Enter name of html file. DO NOT WRITE .html.");
        String fileName = in.nextLine();
        String htmlDirectoried = fileName;

        if(htmlDirectory) {
            htmlDirectoried = "html\\" + fileName;
        }

        if(sub_doc_directory) {
            htmlDirectoried = "html\\sub_documentation\\" + fileName;
        }

        in.close();

        String inputFilePath = "C:\\Users\\sergy\\WebstormProjects\\website\\" + htmlDirectoried + ".html";
        String outPutFilePath = "C:\\Users\\sergy\\WebstormProjects\\website\\extracted_content\\" + fileName + "_content.txt";
        System.out.println(outPutFilePath);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder htmlContent = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
            reader.close();

            Document doc = Jsoup.parse(htmlContent.toString());

            Elements bodyElement = doc.select("body");
            String bodyText = bodyElement.text();

            String cleanedText = bodyText.trim();

            FileWriter writer = new FileWriter(outPutFilePath);
            writer.write(cleanedText);
            writer.close();

            System.out.println("Body information saved to: " + outPutFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
