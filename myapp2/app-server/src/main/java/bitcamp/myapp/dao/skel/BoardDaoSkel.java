package bitcamp.myapp.dao.skel;


import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class BoardDaoSkel {
  private BoardDao boardDao;

  public BoardDaoSkel(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();
    Board board = null;
    int no = 0;
    switch (command) {
      case "insert":
        board = (Board) in.readObject();
        boardDao.insert(board);
        out.writeUTF("success");
        break;
      case "list":
        List<Board> list = boardDao.list();
        out.writeUTF("success");
        out.writeObject(list);
        break;
      case "get":
        no = in.readInt();
        board = boardDao.findBy(no);
        if (board != null) {
          out.writeUTF("success");
          out.writeObject(board);
        } else {
          out.writeUTF("fail");
        }
        break;
      case "update":
        board = (Board) in.readObject();
        if (boardDao.update(board)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
        break;
      case "delete":
        no = in.readInt();
        if (boardDao.delete(no)) {
          out.writeUTF("success");
        } else {
          out.writeUTF("fail");
        }
        break;
      default:
        out.writeUTF("error");
        out.writeUTF("무효한 명령입니다.");
    }

    out.flush();
  }
}
