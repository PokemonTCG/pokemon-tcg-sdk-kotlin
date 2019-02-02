package io.pokemontcg.model


enum class SubType(internal var text: String? = null) {
    EX("EX"),
    SPECIAL("Special"),
    RESTORED("Restored"),
    LEVEL_UP("Level Up"),
    MEGA("MEGA"),
    TECHNICAL_MACHINE("Technical Machine"),
    ITEM("Item"),
    STADIUM("Stadium"),
    SUPPORTER("Supporter"),
    STAGE_1("Stage 1"),
    STAGE_2("Stage 2"),
    GX("GX"),
    POKEMON_TOOL("Pokémon Tool"),
    BASIC("Basic"),
    LEGEND("LEGEND"),
    BREAK("BREAK"),
    ROCKETS_SECRET_MACHINE("Rocket's Secret Machine"),
    TAG_TEAM("TAG TEAM"),
    UNKNOWN;

    val displayName: String
        get() = text ?: name.toLowerCase().capitalize()


    companion object {
        val VALUES by lazy { values() }

        fun find(text: String): SubType {
            val subtype = VALUES.find { it.text.equals(text, true) } ?: UNKNOWN
            if (subtype == UNKNOWN) {
                subtype.text = text
            }
            return subtype
        }
    }
}