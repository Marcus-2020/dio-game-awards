package me.dio.gameawards.service;

import me.dio.gameawards.domain.model.Game;

import java.util.List;

public interface GameService extends CrudService<Game> {
    void vote(Long id);
}
