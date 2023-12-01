package vara17.pokedexmvp

import org.junit.Assert.assertEquals
import org.junit.Test
import vara17.pokedexmvp.model.data.Type
import vara17.pokedexmvp.model.data.TypeInfo
import vara17.pokedexmvp.model.network.map.PokemonMapper


class PokedexTest {

    @Test
    fun `getTypes should return comma-separated names`() {
        // Arrange
        val type1 = Type(TypeInfo("Fire"))
        val type2 = Type(TypeInfo("Water"))
        val type3 = Type(TypeInfo("Grass"))
        val typesList = listOf(type1, type2, type3)

        // Act
        val result = PokemonMapper.getTypes(typesList)

        // Assert
        assertEquals("Fire,Water,Grass", result)
    }

    @Test
    fun `getTypes should handle empty string`() {
        // Arrange
        val typesList = emptyList<Type>()

        // Act
        val result = PokemonMapper.getTypes(typesList)

        // Assert
        assertEquals("", result)
    }

    @Test
    fun `getTypes should handle list with a single element`() {
        // Arrange
        val type = Type(TypeInfo("Electric"))
        val typesList = listOf(type)

        // Act
        val result = PokemonMapper.getTypes(typesList)

        // Assert
        assertEquals("Electric", result)
    }
}