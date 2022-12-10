package me.dio.gameawards.service.impl;

import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.domain.model.GameRepository;
import me.dio.gameawards.service.GameService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl extends BaseCrudService<Game, GameRepository> implements GameService {

    public GameServiceImpl(GameRepository repository) {
        super(repository);
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes() + 1);

        update(id, gameDb);
    }

    @Override
    public List<Game> findAll() {
        return super.repository.findAll(Sort.by(Direction.DESC, "votes"));
    }
}
