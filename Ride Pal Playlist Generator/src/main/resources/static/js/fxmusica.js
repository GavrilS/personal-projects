/*--- Extended Code ---*/
jQuery(document).ready(function () {

    "use strict";

    /*--------------------------------------------
    		Initilize Players
    ---------------------------------------------*/
    var IconMarkup = '<div class="option-icon-list option-icon-list-one">';
    IconMarkup += '<div class="payment-option-wrap">';
    IconMarkup += '<a class="payment-option-btn" data-lol="10"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a>';
    IconMarkup += '</div>';
    IconMarkup += '<a href="javascript:void(0)"><i class="fa fa-download" aria-hidden="true"></i></a>';
    IconMarkup += '</div>';

    var IconMarkupSingle = '<div class="option-icon-list">';
    IconMarkupSingle += '<span class="playlist-icon"></span>';
    IconMarkupSingle += '</div>';

    // new jPlayerPlaylist({
    //     jPlayer: "#player-one",
    //     cssSelectorAncestor: ".player-layout-one"
    // }, [
    //     {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         duration: '1:50'
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         duration: '2:50'
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         duration: '3:40'
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         duration: '3:20'
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         duration: '1:60'
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         duration: '5:00'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         duration: '1:0'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         duration: '2:10'
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     loop: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-two",
    //     cssSelectorAncestor: ".player-layout-two"
    // }, [
    //     {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkup
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-three",
    //     cssSelectorAncestor: ".player-layout-three"
    // }, [
    //     {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-four",
    //     cssSelectorAncestor: ".player-layout-four"
    // }, [
    //     {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         duration: '2:50'
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         duration: '1:50'
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         duration: '3:40'
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         duration: '3:20'
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         duration: '1:60'
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         duration: '5:00'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         duration: '1:0'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         duration: '2:10'
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-five",
    //     cssSelectorAncestor: ".player-layout-five"
    // }, [
    //     {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         duration: '3:40'
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         duration: '1:50'
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         duration: '2:50'
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         duration: '3:20'
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         duration: '1:60'
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         duration: '5:00'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         duration: '1:0'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         duration: '2:10'
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-six",
    //     cssSelectorAncestor: ".player-layout-six"
    // }, [
    //     {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkup
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-seven",
    //     cssSelectorAncestor: ".player-layout-seven"
    // }, [
    //     {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         duration: '1:60'
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         duration: '1:50'
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         duration: '2:50'
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         duration: '3:20'
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         duration: '3:40'
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         duration: '4:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         duration: '5:00'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         duration: '1:0'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         duration: '3:50'
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         duration: '2:10'
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     loop: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-eight",
    //     cssSelectorAncestor: ".player-layout-eight"
    // }, [
    //     {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkup
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-nine",
    //     cssSelectorAncestor: ".player-layout-nine"
    // }, [
    //     {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkupSingle
    //
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-ten",
    //     cssSelectorAncestor: ".player-layout-ten"
    // }, [
    //     {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkupSingle
    //
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-eleven",
    //     cssSelectorAncestor: ".player-layout-eleven"
    // }, [
    //     {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkupSingle
    //
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkupSingle
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     loop: true,
    //     audioFullScreen: true
    // });
    //
    // new jPlayerPlaylist({
    //     jPlayer: "#player-twelve",
    //     cssSelectorAncestor: ".player-layout-twelve"
    // }, [
    //     {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/12.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "into the dark night",
    //         artist: "Harmony of Despair",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/2.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Hidden",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/3.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Big Buck Bunny Trailer",
    //         artist: "Blender Foundation",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/4.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Finding Nemo Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/5.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "title & staff roll",
    //         artist: "X-Men - Destiny",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/1.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Incredibles Teaser",
    //         artist: "Pixar",
    //         mp3: "./assets/audio/1.mp3",
    //         poster: "./assets/album-art/7.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Tempered Song",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/2.mp3",
    //         poster: "./assets/album-art/8.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/3.mp3",
    //         poster: "./assets/album-art/9.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/4.mp3",
    //         poster: "./assets/album-art/10.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Lentement",
    //         artist: "Miaow",
    //         mp3: "./assets/audio/5.mp3",
    //         poster: "./assets/album-art/11.jpg",
    //         optionIcon: IconMarkup
	// 	}, {
    //         title: "Cyber Sonnet",
    //         artist: "The Stark Palace",
    //         mp3: "./assets/audio/6.mp3",
    //         poster: "./assets/album-art/6.jpg",
    //         optionIcon: IconMarkup
	// 	}
	// ], {
    //     swfPath: "../../dist/jplayer",
    //     supplied: "webmv, ogv, m4v, oga, mp3",
    //     useStateClassSkin: true,
    //     autoBlur: false,
    //     smoothPlayBar: true,
    //     keyEnabled: true,
    //     audioFullScreen: true
    // });

    /*--------------------------------------------
    		Initilize Slimscroll
    ---------------------------------------------*/

    $('.playlist-block').slimScroll({
        height: '370px'
    });

    $('.playlist-block-style-two').each(function () {
        var siblingHeight = $(this).parent('.playlist-wrap').siblings('.player-main-block').innerHeight();

        $(this).slimScroll({
            height: siblingHeight,
        });
    });

    $('.playlist-block-style-three').slimScroll({
        height: '546px'
    });

    $('.playlist-block-style-four').slimScroll({
        height: '420px'
    });

    $('.playlist-block-style-five').slimScroll({
        height: '600px'
    });

    /*--------------------------------------------
    		Playlist Controll Settings
    ---------------------------------------------*/

    $("#playListBtnOne").on('click', function () {
        $("#playListOne").parent().slideToggle("slow");
    });
    $("#playListBtnTwo").on('click', function () {
        $("#playListTwo").parent().slideToggle("slow");
    });

    $("#playListThree").parent().addClass("playlist-hide");
    $("#playListBtnThree").on('click', function () {
        $("#playListThree").parent().slideToggle("slow");
    });

    $("#playListFour").parent().addClass("playlist-hide");
    $("#playListBtnFour").on('click', function () {
        $("#playListFour").parent().slideToggle("slow");
    });

    $("#playListBtnFive").on('click', function () {
        $("#playListFive").parent().toggle("slide");
    });

    $("#playListBtnSix").on('click', function () {
        $("#playListSix").parent().toggle("slide");
    });

    $("#playListBtnSeven").on('click', function () {
        $("#playListSeven").parent().toggle("slide");
    });

    $("#playListBtnNine").on('click', function () {
        $("#playListNine").parent().slideToggle("slow");
    });

    $("#playListBtnEight").on('click', function () {
        $("#playListEight").parent().toggle("slide");
    });

    /*--------------------------------------------
    		Toggole Settings
    ---------------------------------------------*/

    $(".toggleBlock").on('click', function () {
        $(this).toggleClass("active");
    });

    /*--------------------------------------------
    		Payment Option Settings
    ---------------------------------------------*/

    var paymentOption = '<div class="payment-option-block">';
    paymentOption += '<ul class="payment-list">';
    paymentOption += '<li><a href="javascript:void(0)"><i class="fa fa-apple" aria-hidden="true"></i></a></li>';
    paymentOption += '<li><a href="javascript:void(0)"><i class="fa fa-amazon" aria-hidden="true"></i></a></li>';
    paymentOption += '<li><a href="javascript:void(0)"><i class="fa fa-spotify" aria-hidden="true"></i></a></li>';
    paymentOption += '</ul>';
    paymentOption += '</div>';

    $(document).on('click', ".payment-option-btn", function () {
        var pr = $(this).parent();
        $(this).toggleClass('active');
        if (pr.find('div.payment-option-block').length !== 0) {
            $(this).parent().children('.payment-option-block').remove();
            console.log('remove');
        } else {
            console.log('add');
            $(this).parent().append(paymentOption);
        }
    });

    /*--------------------------------------------
    		Social Share Option Settings
    ---------------------------------------------*/
    $(".shareBtn").on('click', function () {
        $(this).toggleClass("active");
        $(".share-option").slideToggle("slow");
    });
});
