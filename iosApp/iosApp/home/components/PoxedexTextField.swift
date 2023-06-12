//
//  PoxedexTextField.swift
//  iosApp
//
//  Created by Tolga Pirim on 9.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PoxedexTextField: View {
    @Binding var text: String
    let placeHolderText: String
    
    var body: some View {
        TextField(
            placeHolderText,
            text: $text,
            onEditingChanged: { _ in }
        )
        .overlay(
            HStack {
                Spacer()
                Image(systemName: "magnifyingglass")
                    .foregroundColor(.gray)
                    .padding(.trailing, 12)
            }
        )
        .textFieldStyle(RoundedBorderTextFieldStyle())
        .padding()
    }
    
}

struct PoxedexTextField_Previews: PreviewProvider {
    static var previews: some View {
        PoxedexTextField(
            text: Binding(get: {""}, set: {value in }),
            placeHolderText: "Search Pokemon"
        )
    }
}
