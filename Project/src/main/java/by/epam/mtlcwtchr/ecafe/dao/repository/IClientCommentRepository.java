package by.epam.mtlcwtchr.ecafe.dao.repository;

import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.entity.Comment;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IClientCommentRepository extends IEntityRepository<Comment> {

    List<Comment> getList() throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    List<Comment> getList(String authorPhone)throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    List<Comment> getList(int elementsOfPage, int page)throws DAOException;
    Optional<Comment> find(int id) throws DAOException;
    Optional<Comment> find(String authorPhone) throws DAOException;
    Optional<Comment> save(Comment comment) throws DAOException;
    Optional<Comment> update(Comment comment) throws DAOException;
    int getCount() throws DAOException;
    boolean delete(int id) throws DAOException;

}
