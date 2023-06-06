//
//  SplashView.swift
//  iosApp
//
//  Created by Tolga Pirim on 5.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import KMMViewModelSwiftUI

struct SplashView: View {
   @StateViewModel var viewModel = SplashViewModel()
    @State var isActive:Bool = false
    
    var body: some View {
        ZStack{
            if self.viewModel.onBoardingCompleted{
                ContentView()
            }else{
                OnBoardingScreen()
            }
            
            if !self.isActive{
                Color("splashScreenBackground").ignoresSafeArea()
            
               Image("logo")
                    .resizable()
                    .scaledToFit()
            }
        }.onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.5) {
                withAnimation {
                    isActive = true
                }
            }
        }
    }
}

struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
    }
}
