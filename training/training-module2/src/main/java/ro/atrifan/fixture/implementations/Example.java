package ro.atrifan.fixture.implementations;

import ro.atrifan.fixture.util.Format;
import ro.atrifan.fixture.util.RowWrapper;
import ro.atrifan.fixture.util.Tools;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Created by atrifan on 10/7/2015.
 */
public class Example {

    private RowWrapper row;
    public Example(RowWrapper row) {
        this.row = row;
    }

    public void setVariable() {
        String variableName = row.getCell(1).getText();
        String variableValue = Tools.variableReplacer(row.getCell(2).getText());
        System.setProperty(variableName, variableValue);
    }

    public void assertVariableEquals() {

        Format format = new Format();
        if (row.getNoCells() == 3) {
            String variableName = row.getCell(1).getText();
            String expectedValue = Tools.variableReplacer(row.getCell(2).getText());

            if ((expectedValue.contains("{")) || (expectedValue.contains("<"))) {
                expectedValue = Tools.fromHtml(expectedValue);
            }

            //TODO: I COULD SOME JSON I COULD SOME XML

            if (System.getProperties().containsKey(variableName)) {
                String variableValue = System.getProperty(variableName);
                if (!variableValue.equals(expectedValue)) {
                    format.wrong(row.getCell(2),
                            System.getProperty(variableName));
                } else {
                    format.right(row.getCell(2));
                }

            } else {
                format.error(row.getCell(0), variableName
                        + " not found in saved variables queue.");
            }

        } else {
            format.error(row.getCell(0),
                    "assertEquals should use 3 cells only.");
        }

    }

    public void assertEquals() {

        Format format = new Format();
        if (row.getNoCells() == 3) {
            String variableName = Tools.variableReplacer(row.getCell(1).getText());
            String expectedValue = Tools.variableReplacer(row.getCell(2).getText());

            if ((expectedValue.contains("{")) || (expectedValue.contains("<"))) {
                expectedValue = Tools.fromHtml(expectedValue);
            }

            //TODO: I COULD SOME JSON I COULD SOME XML

            if (!variableName.equals(expectedValue)) {
                format.wrong(row.getCell(2),
                        System.getProperty(variableName));
            } else {
                    format.right(row.getCell(2));
            }

        } else {
            format.error(row.getCell(0),
                    "assertEquals should use 3 cells only.");
        }

    }

    public void GET() {
        String url = Tools.variableReplacer(row.getCell(1).getText());
        System.out.println(url);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
        if(System.getProperties().contains("Accept")) {
            headers.add("Accept", System.getProperty("Accept"));
        } else {
            headers.add("Accept", "application/json");
        }

        Format format = new Format();
        format.replace(row.getCell(1));
        makeHttpCall(url, "GET", headers, null);
    }

    public void POST() {
        //TODO 3: implement POST - write also fitnesse test to include POST
    }

    public void DELETE() {
        //TODO 4: implement DELETE - write also fitnesse test to include DELETE
    }

    public void makeHttpCall(String path, String method, MultivaluedMap<String, Object> headerObject, String body) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path(path);
        Invocation.Builder request = target.request().headers(headerObject);
        Response response = request.build(method).invoke();
        System.setProperty("statusCode", response.getStatus()+"");
    }

}
