package com.example.vehichleslist

import android.widget.ImageView
import com.example.vehichleslist.network.Logo
import com.squareup.picasso.Picasso

/**
 * fun setAndGetUriByBrandParsingListOfLogoAndImageView(logoDataApi: List<Logo>?,brand: String,logo: ImageView)
 */
fun setAndGetUriByBrandParsingListOfLogoAndImageView(
    logoDataApi: List<Logo>?,
    brand: String,
    logo: ImageView
) {
    logoDataApi?.forEach {
        if (it.name.equals(brand, ignoreCase = true)) {
            Picasso.get().load(it.logo).into(logo)
            return
        }
    }
    logo.setImageResource(R.drawable.ic_car)
}