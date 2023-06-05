//
//  SplashView.swift
//  iosApp
//
//  Created by Tolga Pirim on 5.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SplashView: View {
    @State var isActive: Bool = false
    
    var body: some View {
        ZStack{
            if self.isActive{
                ContentView()
            }else{
                Color("splashScreenBackground").ignoresSafeArea()
            
               Image("logo")
                    .resizable()
                    .scaledToFit()
            }
        }.onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.5) {
                withAnimation {
                    self.isActive = true
                }
            }
        }
    }
}

struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView(isActive: false)
    }
}
