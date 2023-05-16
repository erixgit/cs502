import java.sql.*;
import java.util.HashMap;
import java.util.Date;

public class App {
    static Connection con;
    static String context;
    static String object;
    static String message;
    static String query;
    static String queryTail;
    static String schema = " dbo.";
    static HashMap<String, String> methodMap = new HashMap<>();

    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://THINKPAD\\SQLEXPRESS:52188;databaseName=cs560;TrustServerCertificate=True", "readonly", "hola");
            methodMap = getMethods(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new TwoTextAreaButton();
    }

    static String execute(String qy) {
        String sql = qy;
        boolean okay = false;
        String result = "";
        
        try {
            parseQuery(sql);

            People person1 = new People();
            People context = new People();

            person1.id = getId(App.object);
            context.id = getId(App.context);

            if (!person1.id.equals(context.id)) {
                if (methodMap.get(message).equals("public")) {
                    okay = true;
                }
            } else if (methodMap.containsKey(message)) {
                okay = true;
            }

            if (okay == true) {
                String q = query + schema + message + "('" + person1.id + "')" + queryTail;

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(q);

                while (rs.next()) {
                    result = rs.getString(1);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    static HashMap<String, String> getMethods(Connection con) {
        HashMap<String, String> returnMap = new HashMap<>();

        try {
            String q;
            q = "select methodName from Public_Methods_People";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                returnMap.put(rs.getString(1), "public");
            }

            rs.close();
            stmt.close();

            q = "select methodName from Private_Methods_People";

            stmt = con.createStatement();
            rs = stmt.executeQuery(q);

            while (rs.next()) {
                returnMap.put(rs.getString(1), "private");
            }

            rs.close();
            stmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        return returnMap;
    }

    static String getId(String name) {
        String result = "";
        
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select top 1 id from people where name = '" + name + "'");

            while (rs.next()) {
                result = rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    static void parseQuery(String sql) {
        StringBuilder object = new StringBuilder();
        StringBuilder message = new StringBuilder();
        StringBuilder query = new StringBuilder();
        StringBuilder queryTail = new StringBuilder();
        StringBuilder context = new StringBuilder();
        boolean begin = false;
        boolean obj = false;
        boolean mes = false;
        boolean beginContext = false;
        boolean tail = false;

        for (char c : sql.toCharArray()) {
            if (c == '{') {
                beginContext = true;
                continue;
            }

            if (c == '}') {
                continue;
            }
            if (beginContext == false) {
                context.append(c);
                continue;
            }

            if (c == '[') {
                begin = true;
                obj = true;
                tail = false;
                continue;
            }
            if (c == ']') {
                begin = false;
                tail = true;
                continue;
            }

            if (tail == true) {
                queryTail.append(c);
                continue;
            }

            if (begin == true) {
                if (c == ' ') {
                    mes = true;
                    obj = false;
                    continue;
                }
                if (obj == true) {
                    object.append(c);
                }
                if (mes == true) {
                    message.append(c);
                }
            } else {
                query.append(c);
            }
        }

        App.object = object.toString();
        App.message = message.toString();
        App.query = query.toString();
        App.context = context.toString();
        App.queryTail = queryTail.toString();
    }

//     static void parseSql(String sql) {
//         StringBuilder object = new StringBuilder();
//         StringBuilder message = new StringBuilder();
//         StringBuilder query = new StringBuilder();
//         boolean begin = false;
//         boolean obj = false;
//         boolean mes = false;

//         for (char c : sql.toCharArray()) {
//             if (c == '[') {
//                 begin = true;
//                 obj = true;
//                 continue;
//             }
//             if (c == ']') {
//                 begin = false;
//                 continue;
//             }
//             if (begin == true) {
//                 if (c == ' ') {
//                     mes = true;
//                     obj = false;
//                     continue;
//                 }
//                 if (obj == true) {
//                     object.append(c);
//                 }
//                 if (mes == true) {
//                     message.append(c);
//                 }
//             }
//             else {
//                 query.append(c);
//             }
//         }

//         App.object = object.toString();
//         App.message = message.toString();
//         App.query = query.toString();
//     }
}

class People {
    public String id;
    public String name;
    public String state;
    private Date dob;
    public int income;

    People() {
        dob = new Date();
    }
}