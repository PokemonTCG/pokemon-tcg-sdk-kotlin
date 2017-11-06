# Pokémon TCG SDK

This is the Pokémon TCG SDK Kotlin implementation. It is a wrapper around the Pokémon TCG API of [pokemontcg.io](http://pokemontcg.io)

## Installation
Add this line to your `dependencies {...}` block in your `build.gradle` file

```groovy
compile 'io.pokemontcg:pokemon-tcg-sdk-kotlin:1.0.6'
```

## Usage

### Cards:
  
```kotlin
val pokemon = Pokemon()

val cards = pokemon.card().all()
```
There is also support for RxJava2

```kotlin
pokemon.card().observeAll()
	.subscribe({ cards ->
		// Do something...
	}, { t -> 
		// Some error...
	})
```

Query Support

```kotlin
val cards = pokemon.card()
        .where {
            nationalPokedexNumber = 150
            hp = 80.gt()
            // Alternatively "Water|Electric|Fire"
            types = "Water" or "Electric" or "Fire" 
            ...
        }
        .all() // or .observeAll() for RxJava2
	
```
You can find all the applicable query fields in this file: [CardQueryBuilder.kt](https://github.com/r0adkll/pokemon-tcg-sdk-kotlin/blob/439253ca47ecee547def64e11bb6c760e77514db/library/src/main/kotlin/io/pokemontcg/requests/CardQueryBuilder.kt)

Find a single card by id:

```kotlin
val card = pokemon.card()
	.find("xy7-54") // or .observeFind("xy7-54") for RxJava2
```

### Sets:

```kotlin
val pokemon = Pokemon()

val sets = pokemon.set().all()
```

RxJava2 Support

```kotlin
pokemon.set().observeAll()
	.subscribe({ sets ->
		// Do something...
	}, { t-> 
		// Some error...
	})
```
Query Support

```kotlin
val sets = pokemon.set()
	.where {
		totalCards = 100.gt()
		standardLegal = true
		expandedLegal = true
		...
	}
	.all() // or .observeAll() for RxJava2
```
You can find all the applicable query fields in this file: [CardSetQueryBuilder.kt](https://github.com/r0adkll/pokemon-tcg-sdk-kotlin/blob/master/library/src/main/kotlin/io/pokemontcg/requests/CardSetQueryBuilder.kt)

Find a single set by code

```kotlin
val set = pokemon.set().find("xy1") // or .observeFind("xy1") for RxJava2
```

### Types:
Get a list of the available Pokémon card types

```kotlin
val pokemon = Pokemon()

// Get the list of card types i.e. Fire, Water, Electric, etc...
val types = pokemon.type().all() // .observeAll() for RxJava2

// Get a list of availble card super types i.e. Pokémon, Trainer, Energy
val superTypes = pokemon.superType().all() // .observeAll()

// Get a list of card subtypes i.e. Basic, Stage 1, Item, Supporter, etc...
val subTypes = pokemon.subType().all() // .observeAll()
```

### Helper Extension Functions
There are a few extension functions to help simplify your queries

```kotlin
infix fun String.and(): String = ...
infix fun String.or(): String = ...
```

These functions allow you to compose and/or query options i.e.

#### And

```kotlin
"water,fire,electric"
```

#### Or

```kotlin
"water|fire|electric"
```

#### Usage

```kotlin
types = "water" or "fire" or "electric"
types = "water" and "fire" and "fighting"
```

A few of the number based query options allow for 4 options:

* `gt` - Greater than
* `gte` - Greater than or equal to
* `lt` - Less than
* `lte` - Less than equal

and you can use the following extension functions to better streamline them:

```kotlin
fun Int.gt(): String = ...
fun Int.gte(): String = ...
fun Int.lt(): String = ...
fun Int.lte(): String = ...

hp = 80.gt()
attackDamage = 100.lte()
```

## Configuration
You can override the URL and the http logging level by passing the `Config` class instance when creating your `Pokemon` class instance like so:

```kotlin
val config = Config(apiUrl = "http://example.com/api/v1/", logLevel = Level.BODY)
val pokemon = Pokemon(config)
...
```