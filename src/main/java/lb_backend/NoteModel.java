package lb_backend;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

class NoteModel {

    private Sql2o sql2o;

    NoteModel() {
        this.sql2o = new Sql2o("jdbc:mysql://my_mysql:3306/lb_backend", "root", "pass");
    }

    Note createNote(String body) {
        try (Connection conn = sql2o.open()) {
            long newId = (Long)conn.createQuery("insert into notes (body) VALUES (:body)",true)
                    .addParameter("body", body)
                    .executeUpdate()
                    .getKey();
            conn.close();
            return new Note(newId,body);
        }
    }

    List<Note> getAllNotes() {
        try (Connection conn = sql2o.open()) {
            List<Note> ret = conn.createQuery("select id, body from notes")
                    .executeAndFetch(Note.class);
            conn.close();
            return ret;
        }
    }

    List<Note> getNotesWithBody(String body) {
        body = "%"+body+"%";
        try (Connection conn = sql2o.open()) {
            List<Note> ret = conn.createQuery("select * from notes where body like :body")
                    .addParameter("body", body)
                    .executeAndFetch(Note.class);
            conn.close();
            return ret;
        }
    }

    Note getANote(int noteId) throws NoteNotFoundException{
        try (Connection conn = sql2o.open()) {
            List<Note> notes = conn.createQuery("select * from notes where id = :noteId")
                    .addParameter("noteId", noteId)
                    .executeAndFetch(Note.class);
            conn.close();
            if(notes.size()==0){
                throw new NoteNotFoundException("The requested note does not exist");
            }
            return notes.get(0);
        }
    }


}
