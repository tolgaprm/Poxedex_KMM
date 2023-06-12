//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Tolga Pirim on 12.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCombine


extension HomeScreen {
    @MainActor
    class IOSHomeViewModel : ObservableObject{
        let viewModel: HomeViewModel
        
        @Published var state = HomeScreenState(
            pokemonListOfType: PokemonTypeKt.pokemonTypes,
            searchText: "",
            selectedPokemonType: PokemonTypeKt.pokemonTypes[0],
            selectedOrderType: OrderType.smallestnumber,
            pokemonWithSearchAndTypeAndOrder: [],
            isSearchOrTypeOrOrderApplied: false
        )
        
        
        @Published var pokemonPagings: [Pokemon] =  []
        var hasNextPage: Bool = false
 
        init(viewModel: HomeViewModel) {
            self.viewModel = viewModel
            
        }

        
        func fetchPokemons() {
            viewModel.pokemonRepository.getPokemonPaging.watch{ nullablePagingData in
                guard let list = nullablePagingData?.compactMap({ $0 as? Pokemon }) else {
                    return
                }
                
                self.pokemonPagings = list
                self.hasNextPage = self.viewModel.pokemonRepository.getPokemonPager .hasNextPage
            }
        }
        
        func fetchNextData(){
            self.viewModel.pokemonRepository.getPokemonPager.loadNext()
        }

        public var shouldDisplayNextPage: Bool {
            return hasNextPage
        }
        
        func onEvent(event:HomeScreenEvent){
            self.viewModel.onEvent(event: event)
        }
    }
}
