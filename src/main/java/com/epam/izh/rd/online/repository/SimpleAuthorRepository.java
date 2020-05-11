package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] authorsNew = new Author[count() + 1]; //проверить count
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
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                authors[i] = null;
            }
        }
        Author[] authorsNew = new Author[count() - 1];
        int index = 0;
        for (int i = 0; i < authors.length; i++) { // переделать логику, слишком путанная
            if (authors[i] == null && (index < authorsNew.length)) {
                authorsNew[i] = authors[++index];
            } else {
                if (index >=authorsNew.length){
                    break;
                }
                authorsNew[i] = authors[index];
            }
            index++;
        }
        authors = authorsNew;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
