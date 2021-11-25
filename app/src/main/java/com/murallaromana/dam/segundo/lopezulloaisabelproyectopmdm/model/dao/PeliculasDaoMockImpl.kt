package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao

import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

//public class PersonajesMockImpl implements Personajes Dao
class PeliculasDaoMockImpl : PeliculasDao {

    private val lista = ArrayList<Pelicula>()

    override fun getAll(): ArrayList<Pelicula> {
        lista.addAll(
            listOf(
                Pelicula(
                    0,
                    "Inception",
                    "2010",
                    "148 min.",
                    "Estados Unidos",
                    "Christopher Nolan",
                    "Christopher Nolan",
                    "Hans Zimmer",
                    "Wally Pfister",
                    "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page, " +
                            "Ken Watanabe, Marion Cotillard, Tom Hardy, Cillian Murphy, " +
                            "Tom Berenger, Michael Caine, Dileep Rao, Lukas Haas, " +
                            "Pete Postlethwaite, Talulah Riley, Miranda Nolan",
                    "Ciencia ficción",
                    "Dom Cobb (DiCaprio) es un experto en el arte de " +
                            "apropiarse, durante el sueño, de los secretos del " +
                            "subconsciente ajeno. La extraña habilidad de Cobb le ha " +
                            "convertido en un hombre muy cotizado en el mundo del " +
                            "espionaje, pero también lo ha condenado a ser un " +
                            "fugitivo y, por consiguiente, a renunciar a llevar una " +
                            "vida normal. Su única oportunidad para cambiar de vida " +
                            "será hacer exactamente lo contrario de lo que ha hecho " +
                            "siempre: la incepción, que consiste en implantar una " +
                            "idea en el subconsciente en lugar de sustraerla. Sin " +
                            "embargo, su plan se complica debido a la intervención " +
                            "de alguien que parece predecir cada uno de sus " +
                            "movimientos, alguien a quien sólo Cobb podrá descubrir.",
                    "8.0",
                    "https://m.media-amazon.com/images/I/71SBgi0X2KL._SL1200_.jpg",
                    "https://www.youtube.com/watch?v=YoHD9XEInc0"
                ),
                Pelicula(
                    0,
                    "Dunkirk",
                    "2017",
                    "107 min.",
                    "Reino Unido",
                    "Christopher Nolan",
                    "Christopher Nolan",
                    "Hans Zimmer",
                    "Hoyte van Hoytema",
                    "Fionn Whitehead, Mark Rylance, Kenneth Branagh, " +
                            "Tom Hardy, Cillian Murphy, Barry Keoghan, Harry Styles, " +
                            "Jack Lowden, Aneurin Barnard, James D'Arcy, Tom Glynn-Carney, " +
                            "Bradley Hall, Damien Bonnard, Jochum ten Haaf, Michel Biel, " +
                            "James Bloor, Luke Thompson, Billy Howle, Mikey Collins, " +
                            "Bobby Lockwood, Will Attenborough, Tom Nolan, Matthew Marsh, " +
                            "Adam Long, Miranda Nolan, Jack Cutmore-Scott, Michael Fox, " +
                            "Brian Vernel, Elliott Tittensor, Kevin Guthrie, Harry Richardson, " +
                            "Richard Sanderson, Kim Hartman, Calam Lynch, Charley Palmer Rothwell, " +
                            "John Nolan, Bill Milner, Jack Riddiford, Harry Collett, Eric Richard",

                    "Bélico",
                    "Año 1940, en plena 2ª Guerra Mundial. En las playas de Dunkerque, cientos " +
                            "de miles de soldados de las tropas británicas y francesas se encuentran " +
                            "rodeados por el avance del ejército alemán, que ha invadido Francia. " +
                            "Atrapados en la playa, con el mar cortándoles el paso, las tropas se " +
                            "enfrentan a una situación angustiosa que empeora a medida que el enemigo " +
                            "se acerca.",
                    "7.0",
                    "https://m.media-amazon.com/images/I/61jphewUR6L._AC_SL1111_.jpg",
                    "https://www.youtube.com/watch?v=F-eMt3SrfFU"
                ),
                Pelicula(
                    0,
                    "Split",
                    "2016",
                    "116 min.",
                    "Estados Unidos",
                    "M.Night Shyamalan",
                    "M. Night Shyamalan",
                    "West Thordson",
                    "Mike Gioulakis",
                    "James McAvoy, Anya Taylor-Joy, Betty Buckley, Brad William Henke, " +
                            "Haley Lu Richardson, Sebastian Arcelus, Lyne Renee, Neal Huff, " +
                            "Jessica Sula, Peter Patrikios, Bruce Willis, Izzie Coffey, " +
                            "Ukee Washington, M. Night Shyamalan, Robert Michael Kelly, Rosemary Howard, " +
                            "Jerome Gallman, Kate Jacoby, Kash Goins, Roy James Wilson, " +
                            "Christopher Lee Philips, Julie Potter, Ameerah Briggs, Nakia Dillard, " +
                            "Robin Rieger, Emlyn Elisabeth Morinelli",
                    "Thriller",
                    "A pesar de que Kevin (James McAvoy) le ha demostrado a su psiquiatra de " +
                            "confianza, la Dra. Fletcher (Betty Buckley), que posee 23 personalidades " +
                            "diferentes, aún queda una por emerger, decidida a dominar a todas las demás. " +
                            "Obligado a raptar a tres chicas adolescentes encabezadas por la decidida y " +
                            "observadora Casey (Anya Taylor-Joy), Kevin lucha por sobrevivir contra " +
                            "todas sus personalidades y la gente que le rodea, a medida que las paredes " +
                            "de sus compartimentos mentales se derrumban.",
                    "6.5",
                    "https://m.media-amazon.com/images/I/61aXJhiP1JL._AC_SY741_.jpg",
                    "https://www.youtube.com/watch?v=84TouqfIsiI"
                ),
                Pelicula(
                    0,
                    "Lady in the Water",
                    "2006",
                    "110 min.",
                    "Estados Unidos",
                    "M. Night Shyamalan",
                    "M.Night Shyamalan",
                    "James Newton Howard",
                    "Christopher Doyle",
                    "Paul Giamatti, Bryce Dallas Howard, Cindy Cheung, Sarita Choudhury, " +
                            "Jeffrey Wright, M. Night Shyamalan, Bob Balaban, Freddy Rodriguez, " +
                            "Mary Beth Hurt, Bill Irwin, Jared Harris, Joseph D. Reitman, " +
                            "Noah Gray-Cabey, Grant Monohon, John Boyd, Ethan Cohn, June Kyoto Lu, " +
                            "Tovah Feldshuh, Tom Mardirosian, Carla Jimenez, Monique Gabriela Curnen, " +
                            "Marilyn Torres, George Bass, Joel Garland, James Breen, Kevin Frank, " +
                            "Brian Weaver, Jeremy Howard, Brian Steele, Kurt Carley, Doug Jones, " +
                            "Nick Perri, Walter Lafty",
                    "Fantástico",
                    "Cleveland Heep (Paul Giamatti), el encargado de un bloque de apartamentos, " +
                            "descubre una tarde a una ninfa (Bryce Dallas Howard) en la piscina de la " +
                            "urbanización. La criatura está inmersa en un viaje que podría devolver la " +
                            "esperanza a nuestro mundo, pero para completarlo necesitará la ayuda de " +
                            "Cleveland y de todos los vecinos",
                    "4",
                    "https://http2.mlstatic.com/D_NQ_NP_926655-MLM27398161115_052018-O.jpg",
                    "https://www.youtube.com/watch?v=pKR1mfrfGAo"
                )
            )
        )
        return lista
    }
}