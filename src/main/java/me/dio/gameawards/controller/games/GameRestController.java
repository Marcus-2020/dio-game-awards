package me.dio.gameawards.controller.games;

import me.dio.gameawards.controller.BaseRestController;
import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameRestController extends BaseRestController {

    private final GameService businessLayer;

    public GameRestController(GameService businessLayer) {
        this.businessLayer = businessLayer;
    }

    @GetMapping("games")
    public ResponseEntity<List<Game>> findAll() {
        var games = businessLayer.findAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id) {
        var game = businessLayer.findById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("games")
    public ResponseEntity<Game> insert(@RequestBody Game game) {
        businessLayer.insert(game);
        return ResponseEntity.status(202).body(game);
    }

    @PutMapping("games/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
        businessLayer.update(id, game);
        return ResponseEntity.status(200).body(game);
    }

    @PatchMapping("games/{id}/vote")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        businessLayer.vote(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("games/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        businessLayer.delete(id);
        return ResponseEntity.ok(null);
    }
}
