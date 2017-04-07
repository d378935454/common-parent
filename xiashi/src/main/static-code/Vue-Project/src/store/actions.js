/**
 * Created by bean on 2017/2/19.
 */
import * as types from './mutation-types'

export default {
  addTotalTime ({commit}, time) {
    commit(types.ADD_TOTAL_TIME, time)
  },
  decTotalTime ({commit}, time) {
    commit(types.DEC_TOTAL_TIME, time)
  },
  savePlan ({commit}, plan) {
    commit(types.SAVE_PLAN, plan)
  },
  deletePlan ({commit}, plan) {
    commit(types.DELETE_PLAN, plan)
  },
  updateTitle ({commit}, title) {
    commit(types.UPDATE_TITLE, title)
  }
}
