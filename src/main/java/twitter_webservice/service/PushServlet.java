package twitter_webservice.service;

//import org.omnifaces.cdi.Push;
//import org.omnifaces.cdi.PushContext;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//@WebServlet(name="PushServlet", urlPatterns="/pushServlet")
//public class PushServlet extends HttpServlet {
//
//    @Inject
//    @Push(channel="pushChannel")
//    private PushContext channel;
//
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException, ServletException {
//
//        String newItem = req.getParameter("newItem");
//
//        if ((newItem == null) || (newItem =="")) {
//
//            newItem = "Java EE Programming";
//        }
//
//        Map<String, String> pushData = new HashMap<String, String>();
//        pushData.put("pushItem", newItem);
//        channel.send(pushData);
//
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//        out.println("<html><h3>The item is pushed to the JSF page: " +
//                newItem + "</h3></html>");
//    }
//}
