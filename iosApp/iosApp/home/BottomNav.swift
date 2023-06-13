//
//  HomeScreen.swift
//  iosApp
//
//  Created by Tolga Pirim on 7.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BottomNav: View {
    @State private var selectedTab = 0
    
    var body: some View {
        TabView(selection: $selectedTab) {
           HomeScreen()
            .tabItem {
                if selectedTab == 0 {
                    Image("home_selected")
                } else {
                    Image("home_unselected")
                }
                Text(
                    NSLocalizedString(
                        "bottom_bar_poxedex",
                        comment: ""
                    )
                 )
            }
            .onTapGesture {
                selectedTab = 0
            }
            .tag(0)
            
            Text("Favorites Screen")
                .tabItem {
                    if selectedTab == 1 {
                        Image("favorite_selected")
                    } else {
                        Image("favorite_unselected")
                    }
                    Text(
                     NSLocalizedString(
                     "bottom_bar_favorite",
                     comment: ""
                     )
                    )
                    }
                    .tag(1)
                    .onTapGesture {
                        selectedTab = 1
                    }
        }
        .navigationBarBackButtonHidden()
    }
}


struct BottomNavScreen_Previews: PreviewProvider {
    static var previews: some View {
        BottomNav()
    }
}
