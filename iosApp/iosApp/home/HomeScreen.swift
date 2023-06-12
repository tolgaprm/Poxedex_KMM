//
//  HomeScreen.swift
//  iosApp
//
//  Created by Tolga Pirim on 9.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared


struct HomeScreen: View {
    @ObservedObject private var viewModel = IOSHomeViewModel(viewModel: HomeViewModel(coroutineScope: nil))
   
    var body: some View {
        VStack{
            List{
                PoxedexTextField(
                    text: Binding(
                        get: {viewModel.viewModel.stateValue.searchText},
                        set: {value in
                          viewModel.onEvent(event: HomeScreenEvent.OnSearchTextChange(
                          text: value, pagingPokemonList: viewModel.pokemonPagings)
                        )
                       }
                   ),
                placeHolderText: "Pokemon Search"
                )
                
                ForEach(viewModel.pokemonPagings,id: \.id){pokemon in
                    Text(pokemon.name)
                }
                if viewModel.shouldDisplayNextPage {
                        nextPageView
                }
            }
            .onAppear {
                viewModel.fetchPokemons()
            }
        }
    }
    
    private var nextPageView: some View {
        HStack {
            Spacer()
            VStack {
                ProgressView()
                Text("Loading next page...")
            }
            Spacer()
        }
        .onAppear(perform: {
            viewModel.fetchNextData()
        })
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
