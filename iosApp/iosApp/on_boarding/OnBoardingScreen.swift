//
//  OnBoardingScreen.swift
//  iosApp
//
//  Created by Tolga Pirim on 6.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import KMMViewModelSwiftUI

struct OnBoardingScreen: View {
    @StateViewModel var viewModel = OnBoardingViewModel()

    var body: some View {
        LazyHStack {
            PageView(
                onBoardingData: viewModel.onBoardingData,
                onClickContinue: {
                    viewModel.onBoardingCompleted()
                }
            )
        }.onTapGesture {
            viewModel.onBoardingCompleted()
        }
    }
}


struct OnBoardingScreen_Previews: PreviewProvider {
    static var previews: some View {
        OnBoardingScreen()
    }
}
