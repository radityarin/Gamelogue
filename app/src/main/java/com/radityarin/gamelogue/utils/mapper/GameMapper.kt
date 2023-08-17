package com.radityarin.gamelogue.utils.mapper

import com.radityarin.gamelogue.data.source.local.entity.store.GameEntity
import com.radityarin.gamelogue.data.source.remote.response.GameResponse
import com.radityarin.gamelogue.domain.model.Game

object GameResponseToDomainMapper : BaseMapper<GameResponse, Game>() {

    override fun map(oldItem: GameResponse): Game {
        return Game().apply {
            oldItem.let { data ->
                id = data.id
                name = data.name
                backgroundImage = data.backgroundImage
                released = data.released
                rating = data.rating
                ratingTop = data.ratingTop
                description = data.description
                publisher = data.publisher.map { it.name }.toString().replace("[","").replace("]","")
                added = data.added
            }
        }
    }
}

object GameDomainToEntityMapper : BaseMapper<Game, GameEntity>() {

    override fun map(oldItem: Game): GameEntity {
        return GameEntity(
            id = oldItem.id,
            name = oldItem.name,
            backgroundImage = oldItem.backgroundImage,
            released = oldItem.released,
            rating = oldItem.rating,
            ratingTop = oldItem.ratingTop,
            description = oldItem.description,
            publisher = oldItem.publisher,
            added = oldItem.added
        )
    }
}

object GameEntityToDomainMapper : BaseMapper<GameEntity, Game>() {

    override fun map(oldItem: GameEntity): Game {
        return Game().apply {
            oldItem.let { data ->
                id = data.id
                name = data.name
                backgroundImage = data.backgroundImage
                released = data.released
                rating = data.rating
                ratingTop = data.ratingTop
                description = data.description
                publisher = data.publisher
                added = data.added
            }
        }
    }
}

