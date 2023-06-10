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
    let onTextChange: (String) -> Void
    let onSearch: (String) -> Void
    
    var body: some View {
            TextField(placeHolderText, text: $text, onEditingChanged: { _ in }, onCommit: {
                onSearch(text)
                }
            )
            .textFieldStyle(.roundedBorder)
            .padding()
            .background(Color.white)
            .cornerRadius(30)
            .overlay (
                HStack {
                    Spacer()
                   Image(systemName: "magnifyingglass")
                        .foregroundColor(.gray)
                        .padding()
                        .padding()
                }
            )
    }
}

struct PoxedexTextField_Previews: PreviewProvider {
    static var previews: some View {
        PoxedexTextField(
            text: Binding(get: {""}, set: {value in }),
            placeHolderText: "Search Pokemon",
            onTextChange: {_ in
                
            },
            onSearch: {_ in
                
            }
        )
    }
}
