package by.epam.mtlcwtchr.ecafe.tags;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCustomTag extends TagSupport {

    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().print(new SimpleDateFormat("yyyy-MM-dd HH:mm Z").format(new Date()));
        } catch (IOException ex) {
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
        }
        return SKIP_BODY;
    }
}
