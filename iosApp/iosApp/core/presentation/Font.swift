//
//  Font.swift
//  iosApp
//
//  Created by Tolga Pirim on 7.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Font {
    static var h4: Font {
        return Font.custom("poppins-medium", size: 26).weight(.medium)
    }
    static var h5: Font {
        return Font.custom("poppins_semibold",size: 21).weight(.semibold)
    }
    static var h3: Font {
        return Font.custom("poppins-medium",size: 32).weight(.medium)
    }
    static var button: Font {
        return Font.custom("poppins_semibold",size: 18).weight(.semibold)
    }
    static var body1: Font {
        return Font.custom("poppins",size: 14).weight(.regular)
    }
    static var body2: Font {
        return Font.custom("poppins",size: 14).weight(.thin)
    }
}
