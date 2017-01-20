package lb_backend;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Api {

    public static void main(String[] args) {
        get("/api/notes", (req, res) -> {
            String query = req.queryParams("query");
            NoteModel model = new NoteModel();
            List<Note> notes;
            if(query!=null){
                notes = model.getNotesWithBody(query);
            }else{
                notes = model.getAllNotes();
            }
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(notes);
        });

        get("/api/notes/:noteId", (req, res) -> {
            try{
                NoteModel model = new NoteModel();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                int noteId;
                try {
                    noteId = Integer.parseInt(req.params(":noteId"));
                } catch (NumberFormatException e) {
                    return "Id must be a valid integer";
                }
                return gson.toJson(model.getANote(noteId));
            }catch(NoteNotFoundException e){
                return e.getMessage();
            }

        });

        post("/api/notes", (req, res) -> {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Note newNote = gson.fromJson(req.body(),Note.class);
            NoteModel model = new NoteModel();
            newNote = model.createNote(newNote.body);
            return gson.toJson(newNote);

        });
    }
}