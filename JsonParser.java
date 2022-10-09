import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;
public class JsonParser {
    ArrayList<Student> students = new ArrayList<>();
    public void parseJSOn(String url) throws ParseException {

        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("Failed" + clientResponse.toString());
        }
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();

        while (it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();
            String first_name = (String) jsonObject.get("first_name");
            double gpa = Double.parseDouble((String)jsonObject.get("gpa"));
            String email = (String) jsonObject.get("email");
            String gender = (String) jsonObject.get("gender");
            Student student = new Student(first_name, gpa, email, gender);
            students.add(student);
        }
    }

    public void search(String name){
        int flag = -1;
        for (int index = 0; index < students.size(); index++) {
            Student student = students.get(index);
            if (student.getFirstName().equals(name)) {
                System.out.println("The student whose firstname is " + name +
                        " is found at position " + (index + 1));
                flag = 0;
            }
        }
        if(flag == -1){
            System.out.println("The student whose firstname is " + name +
                    " is not found");
        }
    }

    public static void main(String[] args) throws ParseException,IOException {
        JsonParser jsonParser = new JsonParser();
        jsonParser.parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json");

        // create a scanner
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What name do you want to search? ");
        String name = keyboard.nextLine();
        jsonParser.search(name);
    }
}

