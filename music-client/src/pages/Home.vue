<template>
  <div class='home'>
    <!--轮播图-->
    <el-carousel class='swiper-container' :interval='4000' type='card' height='280px'>
      <el-carousel-item v-for='(item, index) in swiperList' :key='index'>
        <div @click='gomusictest(item)'>
          <img :src='item.picImg'/>
        </div>
      </el-carousel-item>
    </el-carousel>
    <!--热门歌单-->
    <play-list :playList='songList' title='歌单' path='song-sheet-detail' class='section'></play-list>
    <!--热门歌手-->
    <play-list :playList='singerList' title='歌手' path='singer-detail' class='section'></play-list>
  </div>
</template>

<script >
import PlayList from '../components/PlayList'
import { swiperList, SEARCH } from '../enums'
import { HttpManager } from '../api'
import mixin from '../mixins'

export default {
  name: 'Home',
  mixins: [mixin],
  components: {
    PlayList
  },
  data () {
    return {
      swiperList: swiperList, // 轮播图列表
      songList: [], // 歌单列表
      singerList: [] // 歌手列表
    }
  },
  created () {
    // 获取歌单列表
    this.getSongList()
    // 获取歌手列表
    this.getSingerList()
  },
  methods: {
    gomusictest (item) {
      switch (item.picImg) {
        case '/static/img/blur-1851426_640.6d0d353.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '遗书'}})
          break
        case '/static/img/boy-984293_640.604b2b2.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '起风了'}})
          break
        case '/static/img/concert-768722_640.39fe07b.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '如果声音不记得'}})
          break
        case '/static/img/ipad-605439_640.a38528b.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '赵雷'}})
          break
        case '/static/img/microphone-1209816_640.af9fc63.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '句号'}})
          break
        case '/static/img/music-notes-3221097_640.f691d0b.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '七里香'}})
          break
        case '/static/img/piano-1655558_640.6512795.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '平凡之路'}})
          break
        case '/static/img/turntable-1337986_640.32fb237.jpg':
          this.routerManager(SEARCH, {path: SEARCH, query: {keywords: '玫瑰少年'}})
          break
      }
    },
    getSongList () {
      HttpManager.getSongList()
        .then(res => {
          this.songList = res.sort().slice(0, 10)
        })
        .catch(err => {
          console.error(err)
        })
    },
    getSingerList () {
      HttpManager.getAllSinger()
        .then(res => {
          this.singerList = res.sort().slice(0, 10)
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>

<style lang='scss' scoped>
@import '@/assets/css/home.scss';
</style>
