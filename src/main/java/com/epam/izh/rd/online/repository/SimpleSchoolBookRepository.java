package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooksNew = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, schoolBooksNew, 0, count());
        schoolBooksNew[count()] = book;
        schoolBooks = schoolBooksNew;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int index = 0;
        SchoolBook[] schoolBooksTemp = new SchoolBook[count()];
        for (SchoolBook schoolBook : schoolBooks) {
            String findName = schoolBook.getName();
            if (findName.equals(name)) {
                schoolBooksTemp[index] = schoolBook;
                index++;
            }
        }
        if (index == 0) {
            return new SchoolBook[0];
        }
        SchoolBook[] schoolBooksFind = new SchoolBook[index];
        System.arraycopy(schoolBooksTemp, 0, schoolBooksFind, 0, index);
        return schoolBooksFind;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;
            }
        }
        SchoolBook[] schoolBookNew = new SchoolBook[schoolBooks.length - findByName(name).length];
        int index = 0;
        for (int i = 0; i < schoolBookNew.length; i++) {
            if (schoolBooks[i] == null && (index < schoolBooks.length)) {
                schoolBookNew[i] = schoolBooks[++index];
            } else {
                schoolBookNew[i] = schoolBooks[index];
            }
            index++;
        }
        schoolBooks = schoolBookNew;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
