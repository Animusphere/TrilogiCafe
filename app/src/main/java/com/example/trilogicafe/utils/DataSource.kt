package com.example.trilogicafe.utils


class DataSource{

    companion object{

        fun createDataSet(): ArrayList<ModelDrink>{
            val list = ArrayList<ModelDrink>()
            list.add(
                ModelDrink(
                    "Tea Lychee",
                    "",
                    "https://www.highlandscoffee.com.vn/vnt_upload/product/04_2020/TRATHACHVAI_1.png",
                    "20000"
                )
            )
            list.add(
                ModelDrink(
                    "Tea Peach",
                    "",
                    "https://www.highlandscoffee.com.vn/vnt_upload/product/03_2018/thumbs/270_crop_TRATHANHDAO.png",
                    "20000"
                )
            )

            list.add(
                ModelDrink(
                    "Tea Lotus",
                    "",
                    "https://www.highlandscoffee.com.vn/vnt_upload/product/03_2018/thumbs/270_crop_TRASENVANG.png",
                    "30000"
                )
            )
            list.add(
                ModelDrink(
                    "Ice Bac Xiu",
                    "",
                    "https://www.highlandscoffee.com.vn/vnt_upload/product/04_2020/thumbs/270_crop_Bac_Xiu_Da.png",
                    "10000"
                )
            )
            list.add(
                ModelDrink(
                    "Coffee",
                    "",
                    "https://www.highlandscoffee.com.vn/vnt_upload/product/05_2018/thumbs/270_crop_CFD.png",
                    "5000"
                )
            )

            return list
        }
    }
}