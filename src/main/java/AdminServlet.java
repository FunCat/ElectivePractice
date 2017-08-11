import com.epam.electives.dao.impl.UserDaoImpl;
import com.epam.electives.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rusamaha on 8/11/17.
 */

@Component
public class AdminServlet extends HttpServlet {

    private UserDaoImpl userDao = new UserDaoImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<UserProfile> userList = userDao.findAll();

        res.setContentType("text/html");//setting the content type
        PrintWriter pw=res.getWriter();//get the stream to write the data

//writing html in the stream
        pw.println("<html><body>");
        pw.println("Welcome to Admin panel");
        pw.println("<ul>");
        for (UserProfile userProfile: userList){
            pw.println("<li>");
            pw.println(userProfile.getFirstname());
            pw.println("</li>");
        }
        pw.println("</ul>");
        pw.println("</body></html>");

        pw.close();//closing the stream

    }
}
