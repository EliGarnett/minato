package priv.eli.minato.chain;

import java.util.List;

/**
 * @author Eli
 * @date 2021/5/8
 */
public interface CheckResultNode {

    void doAssemble(List<Domain> domainList);


    void doFilter(List<Domain> domainList);

    default void executeNode(List<Domain> domainList) {
        doAssemble(domainList);
        doFilter(domainList);
    }

}
