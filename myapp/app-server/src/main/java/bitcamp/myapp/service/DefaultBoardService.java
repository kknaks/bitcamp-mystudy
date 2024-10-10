package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DefaultBoardService implements BoardService {

  private BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
  }

  @Transactional
  @Override
  public void add(Board board) throws Exception {
    boardDao.insert(board);
    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.list();
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findBy(boardNo);
  }

  @Transactional
  @Override
  public void increaseViewCount(int boardNo) throws Exception {
    Board board = boardDao.findBy(boardNo);
    if (board != null) {
      boardDao.updateViewCount(board.getNo(), board.getViewCount() + 1);
    }
  }

  @Transactional
  @Override
  public boolean update(Board board) throws Exception {
    if (!boardDao.update(board)) {
      return false;
    }

    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
    return true;
  }

  @Transactional
  @Override
  public void delete(int boardNo) throws Exception {
    boardDao.deleteFiles(boardNo);
    boardDao.delete(boardNo);
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.getFile(fileNo);
  }

  @Transactional
  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    if (!boardDao.deleteFile(fileNo)) {
      return false;
    }
    return true;
  }
}
