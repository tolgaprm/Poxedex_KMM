//
//  HomeScreen.swift
//  iosApp
//
//  Created by Tolga Pirim on 9.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared
struct HomeScreen: View {
    @StateViewModel var viewModel = HomeViewModel()
    var body: some View {
        VStack{
            PoxedexTextField(
                text: Binding(get: {viewModel.state.searchText}, set: {value in
                    viewModel.onEvent(event: HomeScreenEvent.OnSearchTextChange(text: value))
                }),
                placeHolderText: "Search Pokemon",
                onTextChange: { value in
                    
                },
                onSearch: { searchText in
                }
            )
            Spacer()
        }

    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
