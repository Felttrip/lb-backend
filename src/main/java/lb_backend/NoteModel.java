package lb_backend;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Nate on 1/19/2017.
 */
public class NoteModel {

    private Sql2o sql2o;

    public NoteModel() {
        this.sql2o = new Sql2o("127.0.0.1:3306", "root", "pass");
    }

    public Note createNote(String body) {
        try (Connection conn = sql2o.beginTransaction()) {
            int newId = (Integer)conn.createQuery("insert into notes (body) VALUES (:body)",true)
                    .addParameter("body", body)
                    .executeUpdate()
                    .getKey();
            conn.commit();
            return new Note(newId,body);
        }
    }

    public List<Note> getAllNotes() {
        try (Connection conn = sql2o.open()) {
            List<Note> notes = conn.createQuery("select * from notes")
                    .executeAndFetch(Note.class);
            return notes;
        }
    }

    public List<Note> getNotesWithBody(String body) {
        try (Connection conn = sql2o.open()) {
            List<Note> notes = conn.createQuery("select * from notes where body like %:body%")
                    .addParameter("body", body)
                    .executeAndFetch(Note.class);
            return notes;
        }
    }

    public Note getANote(int noteId) {
        try (Connection conn = sql2o.open()) {
            List<Note> notes = conn.createQuery("select * from notes where id = :noteId")
                    .addParameter("noteId", noteId)
                    .executeAndFetch(Note.class);
            return notes.get(0);
        }
    }


}
