//
//  PagerIndicator.swift
//  iosApp
//
//  Created by Tolga Pirim on 7.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PagerIndicator: View {
    let pageCount: Int
    let currentPage: Int

    var body: some View {
        HStack {
            ForEach(0..<pageCount) { index in
                if index == currentPage {
                    SelectedPagerIndicator()
                } else {
                    UnSelectedPagerIndicator()
                }
                Spacer().frame(width: 8)
            }
        }
    }
}

struct SelectedPagerIndicator: View {
    var body: some View {
        RoundedRectangle(cornerRadius: 50)
            .frame(width: 28, height: 9)
            .foregroundColor(Color.primary)
    }
}

struct UnSelectedPagerIndicator: View {
    var body: some View {
        RoundedRectangle(cornerRadius: 9)
            .frame(width: 9, height: 9)
            .foregroundColor(Color(red: 69/255, green: 101/255, blue: 183/255))
    }
}

struct PagerIndicator_Previews: PreviewProvider {
    static var previews: some View {
        PagerIndicator(
        pageCount: 2, currentPage: 0)
    }
}
