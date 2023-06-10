//
//  PageView.swift
//  iosApp
//
//  Created by Tolga Pirim on 7.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PageView: View {
    let onBoardingData:[OnBoardingData]
    let onClickContinue:()-> Void
    var body: some View {
        TabView {
            ForEach(0..<onBoardingData.count) { currentPage in
                let data = onBoardingData[currentPage]
                VStack {
                    Spacer()
                    Image(data.image)
                    Text(data.title).font(.h4).padding(.vertical,16)
                    Text(data.description_).font(.body2).padding(.bottom,16)
                    
                    PagerIndicator(
                        pageCount: onBoardingData.count,
                        currentPage: currentPage
                    ).padding(.bottom,16)
                    
                    if currentPage == 1 {
                        NavigationLink {
                            BottomNav()
                        } label: {
                            Text("Continue").font(.button)
                                .padding(.horizontal, 16)
                                .padding(.vertical, 8)
                                .background(Color.primary)
                                .foregroundColor(.white)
                                .cornerRadius(16)
                        }
                        .onTapGesture {
                            onClickContinue()
                        }
                    }
                    Spacer()
                }
            }
            .padding(.horizontal, 16)
        }
        .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height)
        .tabViewStyle(PageTabViewStyle())
    }
}

struct PageView_Previews: PreviewProvider {
    static var previews: some View {
        PageView(
            onBoardingData: [
                OnBoardingData(
                    image: "onboarding_1",
                    title: "All Pokémon in One Place",
                    description: "Access a vast list of Pokémon from every generation ever made by Nintendo"
                ),
                OnBoardingData(
                    image: "onboarding_1",
                    title: "All Pokémon in One Place",
                    description: "Access a vast list of Pokémon from every generation ever made by Nintendo"
                )
            ],
            onClickContinue: {
                
            }
        )
    }
}
