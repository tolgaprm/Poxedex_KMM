//
//  Colors.swift
//  iosApp
//
//  Created by Tolga Pirim on 7.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color{
    init(hex:Int64, alpha:Double = 1){
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    
    static let primary = Color(hex: colors.EgyptianBlue)
    static let squant = Color(hex: colors.Squant)
    static let allTypes = Color(hex: colors.AllTypes)
    static let water = Color(hex: colors.Water)
    static let dragon = Color(hex: colors.Dragon)
    static let electric = Color(hex: colors.Electric)
    static let fairy = Color(hex: colors.Fairy)
    static let ghost = Color(hex: colors.Ghost)
    static let fire = Color(hex: colors.Fire)
    static let ice = Color(hex: colors.Ice)
    static let grass = Color(hex: colors.Grass)
    static let insect = Color(hex: colors.Insect)
    static let fighter = Color(hex: colors.Fighter)
    static let normal = Color(hex: colors.Normal)
    static let nightFighter = Color(hex: colors.NightFighter)
    static let metal = Color(hex: colors.Metal)
    static let rock = Color(hex: colors.Rock)
    static let psychic = Color(hex: colors.Psychic)
    static let earthy = Color(hex: colors.Earthy)
    static let poisonous = Color(hex: colors.Poisonous)
    static let flying = Color(hex: colors.Flying)
    
}
