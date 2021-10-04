package me.limelier.model

/**
 * A verb describing what occurred on the item to trigger the feed event.
 * @see PartialFeedEvent
 */
public enum class Verb {
    ADD,
    BLOCK,
    EDIT,
    EDITED,
    DELETE,
    FOLLOW,
    HIDE,
    MUTE,
    REMOVE,
    UNBLOCK,
    UNHIDE,
    UPDATE
}