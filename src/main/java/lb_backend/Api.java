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
            NoteModel model = new NoteModel();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(model.getANote(Integer.parseInt(req.params(":noteId"))));
        });

        post("/api/notes/", (req, res) -> {
            NoteModel model = new NoteModel();
            model.createNote(req.body());
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(model.createNote(req.body()));
        });
    }
}