package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Comment;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LeaveCommentCommand extends Command {

    public LeaveCommentCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            Comment comment = new Comment();
            final String authorName = getRequest().getParameter("authorName");
            if (Objects.nonNull(authorName) && !authorName.isEmpty() && !authorName.isBlank()) {
                comment.setAuthorName(authorName);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
            final String authorPhone = getRequest().getParameter("authorPhone");
            if (Objects.nonNull(authorPhone) && !authorPhone.isEmpty() && !authorPhone.isBlank()) {
                comment.setAuthorPhone(authorPhone);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
            final String message = getRequest().getParameter("message");
            if (Objects.nonNull(message) && !message.isEmpty() && !message.isBlank()) {
                comment.setMessage(message);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
            EntityServiceFactory.getInstance().getClientCommentService().save(comment);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/home?status=success");
        } catch (ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }

    }

}
