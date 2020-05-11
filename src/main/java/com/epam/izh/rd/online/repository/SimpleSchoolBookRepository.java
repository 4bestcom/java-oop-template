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
            if (schoolBook != null) {
                String findName = schoolBook.getName();
                if (findName.equals(name)) {
                    schoolBooksTemp[index] = schoolBook;
                    index++;
                }
            }
        }
        if (index == 0) {
            return new SchoolBook[0];
        } else {
            SchoolBook[] schoolBooksFind = new SchoolBook[index];
            System.arraycopy(schoolBooksTemp, 0, schoolBooksFind, 0, index);
            return schoolBooksFind;
        }
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        SchoolBook[] schoolBookNew = new SchoolBook[count() - findByName(name).length];
        for (int i = 0, j = 0; i < schoolBookNew.length; i++, j++) {
            if (schoolBooks[j].getName().equals(name)) {
                i--;
                j++;
            } else {
                schoolBookNew[i] = schoolBooks[j];
            }
        }
        schoolBooks = schoolBookNew;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
