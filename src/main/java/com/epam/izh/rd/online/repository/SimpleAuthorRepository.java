package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] authorsNew = new Author[count() + 1];
        System.arraycopy(authors, 0, authorsNew, 0, count());
        authorsNew[count()] = author;
        authors = authorsNew;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author : authors) {
            String findName = author.getName();
            String findLastName = author.getLastName();
            if (findName.equals(name) && findLastName.equals(lastName)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] authorsNew = new Author[count() - 1];
        for (int i = 0, j = 0; i < authorsNew.length; i++, j++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                i--;
                j++;
            } else {
                authorsNew[i] = authors[j];
            }
        }
        authors = authorsNew;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
