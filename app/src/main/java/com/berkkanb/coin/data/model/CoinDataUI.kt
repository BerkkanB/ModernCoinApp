package com.berkkanb.coin.data.model

import com.google.gson.annotations.SerializedName

data class CoinDataUI(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("asset_platform_id")
    val assetPlatformId: String?,
    val platforms: Map<String, String>,
    @SerializedName("detail_platforms")
    val detailPlatforms: Map<String, DetailPlatform>,
    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Int,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,
    val categories: List<String>,
    @SerializedName("public_notice")
    val publicNotice: String?,
    @SerializedName("additional_notices")
    val additionalNotices: List<String>,
    val description: Description,
    val links: Links,
    val image: Image,
    @SerializedName("country_origin")
    val countryOrigin: String,
    @SerializedName("genesis_date")
    val genesisDate: String,
    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double,
    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double,
    @SerializedName("watchlist_portfolio_users")
    val watchlistPortfolioUsers: Int,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("coingecko_rank")
    val coingeckoRank: Int,
    @SerializedName("coingecko_score")
    val coingeckoScore: Double,
    @SerializedName("developer_score")
    val developerScore: Double,
    @SerializedName("community_score")
    val communityScore: Double,
    @SerializedName("liquidity_score")
    val liquidityScore: Double,
    @SerializedName("public_interest_score")
    val publicInterestScore: Double,
    @SerializedName("public_interest_stats")
    val publicInterestStats: PublicInterestStats,
    @SerializedName("status_updates")
    val statusUpdates: List<Any>,
    @SerializedName("last_updated")
    val lastUpdated: String
)

data class DetailPlatform(
    @SerializedName("decimal_place")
    val decimalPlace: Any?,
    @SerializedName("contract_address")
    val contractAddress: String
)

data class Description(
    val en: String
)

data class Links(
    val homepage: List<String>,
    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,
    @SerializedName("official_forum_url")
    val officialForumUrl: List<String>,
    @SerializedName("chat_url")
    val chatUrl: List<String>,
    @SerializedName("announcement_url")
    val announcementUrl: List<String>,
    @SerializedName("twitter_screen_name")
    val twitterScreenName: String,
    @SerializedName("facebook_username")
    val facebookUsername: String,
    @SerializedName("bitcointalk_thread_identifier")
    val bitcointalkThreadIdentifier: Any?,
    @SerializedName("telegram_channel_identifier")
    val telegramChannelIdentifier: String,
    @SerializedName("subreddit_url")
    val subredditUrl: String,
    @SerializedName("repos_url")
    val reposUrl: ReposUrl
)

data class ReposUrl(
    val github: List<String>,
    val bitbucket: List<Any>
)

data class Image(
    val thumb: String,
    val small: String,
    val large: String
)

data class PublicInterestStats(
    @SerializedName("alexa_rank")
    val alexaRank: Int,
    @SerializedName("bing_matches")
    val bingMatches: Any?
)
